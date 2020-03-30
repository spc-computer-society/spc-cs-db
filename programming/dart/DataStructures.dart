main() {
  var fixed_lst = new List(3);  //This declares a fixed-length list with the size.abstract
  fixed_lst[0] = "apple";  //This adds value to the specific index.
  fixed_lst[1] = "banana";
  fixed_lst[2] = 15;
  print(fixed_lst);
  print(fixed_lst.runtimeType);

  var lst = ["apple", "banana", 15];  //This declares a growable list. Syntax: [].
  print(lst);
  print(lst.runtimeType);

  var map = {"apple": 5, "banana": 15};  //This declares a map. It stores pairs of keys and values. It can contain lists too!
  print(map);
  print(map.runtimeType);
  print(map["banana");

}