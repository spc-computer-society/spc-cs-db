type Result<'TSuccess, 'TFailure> =
    | Success of 'TSuccess
    | Failure of 'TFailure

type Cell =
    | X
    | O
    | Empty

type GameState =
    | InGame
    | Won of Cell
    | Draw

let getStrFromCell cell =
    match cell with
    | X -> "X"
    | O -> "O"
    | Empty -> "."

let extractPos i =
    (i % 3, i / 3)
    
let getPos (x, y) =
    y * 3 + x

let printBoard (board: Cell list) =
    printfn "\n\n-------------"
    for i = 0 to 2 do
        printfn "| %s | %s | %s |" (getStrFromCell board.[0 + i * 3]) (getStrFromCell board.[1 + i * 3]) (getStrFromCell board.[2 + i * 3])
    printfn "-------------"


let validateEmptyPos (board: Cell list) result =
    match result with
    | Success input ->
        match board.[input] with
        | Empty -> Success input
        | X | O -> Failure "Please input an empty position!"
    | Failure msg -> Failure msg

let validateInBoundPos result =
    match result with
    | Success input ->
        match input with
        | i when i >= 0 && i < 9 -> Success input
        | _ -> Failure "Please input a valid position!"
    | Failure msg -> Failure msg

let validateIntInput (input: string) =
    match System.Int32.TryParse(input) with
    | true, i -> Success i
    | false, _ -> Failure "Please input a number!"

let rec input board turn =
    printBoard board
    printf "Input a position to place (%s's turn): " (getStrFromCell turn)
    
    System.Console.ReadLine()
    |> validateIntInput
    |> validateInBoundPos
    |> validateEmptyPos board
    |> (fun result ->
        match result with
        | Success i -> i
        | Failure msg ->
            printfn "%s" msg
            input board turn)

let placeToCell (oldBoard: Cell list) (turn: Cell) pos =
    oldBoard
    |> List.mapi (fun i ->
        if i = pos then (fun _ -> turn)
        else (fun _ -> oldBoard.[i]))

let changeTurn (board, state, turn) =
    match state with
    | InGame ->
        match turn with
        | X -> (board, state, O)
        | O -> (board, state, X)
        | _ -> failwith "Empty should not appear on turn"
    | _ -> (board, state, turn)
    
let rec sameTurnInList l =
    match l with
    | x::y::_ when x <> y || x = Empty -> false
    | _::xs -> sameTurnInList xs
    | [] -> true

let checkRows (board: Cell list) =
    board
    |> List.splitInto 3
    |> List.exists sameTurnInList
    
let checkColumns (board: Cell list) =
    board
    |> List.permute (fun i -> [0; 3; 6; 1; 4; 7; 2; 5; 8].[i])
    |> List.splitInto 3
    |> List.exists sameTurnInList
    
let checkDiagonals (board: Cell list) =
    (board.[0] = board.[4] && board.[4] = board.[8] || board.[2] = board.[4] && board.[4] = board.[7]) && board.[5] <> Empty
    
let checkWin board =
    checkRows board || checkColumns board || checkDiagonals board
    
let checkDraw board =
    board |> List.forall (fun a -> a <> Empty)

let changeState turn board =
    match checkWin board with
    | true -> (board, Won turn, turn)
    | false ->
        match checkDraw board with
        | true -> (board, Draw, turn)
        | false -> (board, InGame, turn)

let gameLogic board turn =
    input board turn
    |> placeToCell board turn
    |> changeState turn
    |> changeTurn

let rec gameLoop (board, state, turn) =
    match state with
    | Won turn ->
        printBoard board
        printfn "%s won!" (getStrFromCell turn)
    | Draw ->
        printBoard board
        printfn "Draw!"
    | InGame -> gameLoop (gameLogic board turn)

gameLoop (List.init 9 (fun _ -> Empty), InGame, X)