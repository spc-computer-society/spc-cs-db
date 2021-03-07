fn main() {
	let arr: [&str; 3] = ["Alfa", "Bravo", "Charlie"];

	for name in &arr { // iterate through a container
		println!("{}", name);
	}

	let mut a = 0;
	while arr[a] != "Bravo" { // while loop
		println!("{}", arr[a]);
		a += 1;
	}

	loop { // infinite loop
		println!("Never gonna give you up");
	}
}