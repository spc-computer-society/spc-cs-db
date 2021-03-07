fn main() {
	let i = 5;
	let i1: i32 = 5; // explicitly set type
	let i2 = 5i32; // using literals to declare
	let f: f32 = 3.53f32;
	let string: &str = "This is a string"; // fixed size string (similar to C char array)
	let string1: String = String::from("This is a string"); // resizable string (similar to C++ std::string)
	let d = 3.53; // f64 (64 bits float)

	println!("{}", i);
	println!("{}", i1);
	println!("{}", i2);
	println!("{}", f);
	println!("{}", string);
	println!("{}", string1);
	println!("{}", d);
}