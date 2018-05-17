<?php

//Base code got from https://github.com/Learn2Crack/android-login-registration-server.git 

class DBOperations{

	 private $host = 'fdb21.awardspace.net';
	 //private $user = 'id5578182_neko';
	 //private $host = '127.0.0.1';
	 private $user = '2719551_manekineko';
	 private $db = '2719551_manekineko';
	 //private $pass = 'androideihm4';
	 private $pass = 'androideihm4';
	 private $conn;

public function __construct() {

	$this -> conn = new PDO("mysql:host=".$this -> host.";dbname=".$this -> db.";charset=UTF8", $this -> user, $this -> pass);
	//mysqli_set_charset($this->conn, "utf8");

}



 public function insertUsernameData($name,$email,$password){

 	$unique_id = uniqid('', true);
    $hash = $this->getHash($password);
    $encrypted_password = $hash["encrypted"];
	$salt = $hash["salt"];

 	$sql = 'INSERT INTO users SET unique_id =:unique_id,name =:name,
    email =:email,encrypted_password =:encrypted_password,salt =:salt,created_at = NOW()';

 	$query = $this ->conn ->prepare($sql);
 	$query->execute(array('unique_id' => $unique_id, ':name' => $name, ':email' => $email,
     ':encrypted_password' => $encrypted_password, ':salt' => $salt));

    if ($query) {
        
        return true;

    } else {

        return false;

    }
 }


 public function checkLogin($email, $password) {

    $sql = 'SELECT * FROM users WHERE email = :email';
    $query = $this -> conn -> prepare($sql);
    $query -> execute(array(':email' => $email));
    $data = $query -> fetchObject();
    $salt = $data -> salt;
    $db_encrypted_password = $data -> encrypted_password;

    if ($this -> verifyHash($password.$salt,$db_encrypted_password) ) {


        $user["name"] = $data -> name;
        $user["email"] = $data -> email;
        $user["unique_id"] = $data -> unique_id;
        return $user;

    } else {

        return false;
    }

 }
 
 public function fetchAllMeals() {

    $sql = 'SELECT * FROM meals';
    $query = $this -> conn -> prepare($sql);
    $query -> execute();

    if($query){
        $meals = array();

        while($row = $query -> fetchObject()) {
        // output data of each row) {
            //echo $data ;
            //echo "<br>"."id: " . $meal->unique_id. " - Name: " . $meal->name. " price: " . $meal->price. "<br>";
            $meal["name"] = $row -> name;
            $meal["description"] = $row -> description;
            $meal["id"] = $row -> sno;
            $meal["price"] = $row -> price;
            $meal["photo"] = $row -> photo;
            $meal["nbVotes"] = $row -> nbVotes;
            $meal["totalVotes"] = $row -> totalVotes;
            $meal["type"] = $row -> type;
			
			//selecting the tags
			$sqltags = 'SELECT * FROM tags WHERE sno IN (SELECT tagID FROM meal_tag WHERE mealID = :mealID)';
			$querytags = $this -> conn -> prepare($sqltags);
			$querytags -> execute(array(':mealID' => $meal["id"]));

			$tags = array();
			while($data = $querytags -> fetchObject()){
				//echo "<br> ----".$data->name;
				$tags[] = $data->name;
			}

			$meal["tagList"] = $tags;
			
            $meals[] = $meal;
        }

        return $meals;
    }
    else{
        return false;
    }

 }
 
 public function fetchMeal($name) {

    $sql = 'SELECT * FROM meals WHERE name = :name';
    $query = $this -> conn -> prepare($sql);
    $query -> execute(array(':name' => $name));
    $data = $query -> fetchObject();
    
    $meal["id"] =  $data -> sno;
	$meal["name"] = $data -> name;
	$meal["description"] = $data -> description;
	$meal["id"] = $data -> sno;
	$meal["price"] = $data -> price;
	$meal["photo"] = $data -> photo;
	
	//selecting the tags
	$sqltags = 'SELECT * FROM tags WHERE sno IN (SELECT tagID FROM meal_tag WHERE mealID = :mealID)';
	$querytags = $this -> conn -> prepare($sqltags);
	$querytags -> execute(array(':mealID' => $meal["id"]));

	$tags = array();
	while($data = $querytags -> fetchObject()){
		//echo "<br> ----".$data->name;
		$tags[] = $data->name;
	}

	$meal["tagList"] = $tags;
    
    
    return $meal;
    

 }
 
 public function fetchMealsOfType($type) {

    $sql = 'SELECT * FROM meals WHERE type = :type';
    $query = $this -> conn -> prepare($sql);
	
    $query -> execute(array(':type' => $type));
    
    if($query){
        $meals = array();
        
        while($row = $query -> fetchObject()) {
        // output data of each row) {
           // echo "<br>dbo : ".$data ;
            //echo "<br>"."id: " . $meal->unique_id. " - Name: " . $meal->name. " price: " . $meal->price. "<br>";
            $meal["name"] = $row -> name;
            $meal["description"] = $row -> description;
            $meal["id"] = $row -> sno;
            $meal["price"] = $row -> price;
            $meal["photo"] = $row -> photo;
            $meal["nbVotes"] = $row -> nbVotes;
            $meal["totalVotes"] = $row -> totalVotes;
            $meal["type"] = $row -> type;
            //echo "<br> old : ".$row -> description." and new : ".$meal["description"];
			
			//selecting the tags
			$sqltags = 'SELECT * FROM tags WHERE sno IN (SELECT tagID FROM meal_tag WHERE mealID = :mealID)';
			$querytags = $this -> conn -> prepare($sqltags);
			$querytags -> execute(array(':mealID' => $meal["id"]));

			$tags = array();
			while($data = $querytags -> fetchObject()){
				//echo "<br> ----".$data->name;
				$tags[] = $data->name;
			}

			$meal["tagList"] = $tags;
				
				
			
            $meals[] = $meal;
        }
        
        return $meals;
    }
    else{
        return false;
    }
    
 }
 public function fetchAllTags() {

echo "entered";
    $sql = 'SELECT * FROM tags';
    $query = $this -> conn -> prepare($sql);
	
    $query -> execute();
    
    if($query){
        $tags = array();
        
        while($tag= $query -> fetchObject()) {
        // output data of each row) {
           // echo "<br>dbo : ".$data ;
            //echo "<br>"."id: " . $meal->unique_id. " - Name: " . $meal->name. " price: " . $meal->price. "<br>";
            

			$tags[] = $tag->name;
                        echo "<br>dbo : ".$tag->name ;
				
        }
        echo "<br>success";
        echo $tags;
        return $tags;
    }
    else{
    echo "error";
        return false;
    }
    
 }



 public function changePassword($email, $password){


    $hash = $this -> getHash($password);
    $encrypted_password = $hash["encrypted"];
    $salt = $hash["salt"];

    $sql = 'UPDATE users SET encrypted_password = :encrypted_password, salt = :salt WHERE email = :email';
    $query = $this -> conn -> prepare($sql);
    $query -> execute(array(':email' => $email, ':encrypted_password' => $encrypted_password, ':salt' => $salt));

    if ($query) {
        
        return true;

    } else {

        return false;

    }

 }

 public function checkUserExist($email){

    $sql = 'SELECT COUNT(*) from users WHERE email =:email';
    $query = $this -> conn -> prepare($sql);
    $query -> execute(array('email' => $email));

    if($query){

        $row_count = $query -> fetchColumn();

        if ($row_count == 0){

            return false;

        } else {

            return true;

        }
    } else {

        return false;
    }
 }

 public function checkMealExist($name){

    $sql = 'SELECT COUNT(*) from meals WHERE name =:name';
    $query = $this -> conn -> prepare($sql);
    $query -> execute(array('name' => $name));

    if($query){

        $row_count = $query -> fetchColumn();

        if ($row_count == 0){

            return false;

        } else {

            return true;

        }
    } else {

        return false;
    }
 }

 public function checkMealTypeExist($type){

    $sql = 'SELECT COUNT(*) from meals WHERE type =:type';
    $query = $this -> conn -> prepare($sql);
    $query -> execute(array('type' => $type));

    if($query){

        $row_count = $query -> fetchColumn();

        if ($row_count == 0){

            return false;

        } else {

            return true;

        }
    } else {

        return false;
    }
 }

 public function getHash($password) {

     $salt = sha1(rand());
     $salt = substr($salt, 0, 10);
     $encrypted = password_hash($password.$salt, PASSWORD_DEFAULT);
     $hash = array("salt" => $salt, "encrypted" => $encrypted);

     return $hash;

}



public function verifyHash($password, $hash) {

    return password_verify ($password, $hash);
}
}




