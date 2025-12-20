

//access modifier : 
// private : can be only accessed within the class 
// not even subcclass or another package 

///public :
/// eveeywhere 
 
/////protceted : 
////allows to access the members within the same package
///as well as from subclass in other packages  => controlled inheriatance
///prevent unrelated class from accessing 

//default :allows access within the package only 
// even outside pacakge , doesnt let access to other subclass
// striclty restricted to current package, 
//as the code written there is technically connected or close  

// Certainly! Here's the table in text tabular format:

// Modifier	    Class	Package	 Subclass	Global
// public	    Yes	        Yes	        Yes     Yes
// protected	Yes	        Yes	        Yes 	No
// (default)	Yes	        Yes	        No	    No
// private	    Yes	        No	        No	    No
public class Main{
    public static void main(String[] args) {
        
    }
}

