import 'dart:io';

main() async {
  var file = File('../_common/TextPrint.txt');  //This opens the file and passes into the variable "file".
  var content = await file.readAsString();  //This reads the content of the file and passes into the variable "content".
  print(content);  //This prints out the content.
}