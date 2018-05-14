package com.ihm.androide.upmc.manekineko.database;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;

/**
 * Created by HAMDANI on 28/04/2018.
 */

public class Meal {

    private int id;
    private String name;
    private float price;
    private String photo;
    //private Drawable drawable;
    private String description;
    //private ArrayList<String> tagList;
    private String[] tagList;
    private int totalVotes;
    private int nbVotes;
    private String type;
    Bitmap bitmap;
/*
CREATE TABLE edamameTags(sno int(11) NOT NULL AUTO_INCREMENT,
                        ref int(11) UNIQUE,
                         PRIMARY KEY (sno),
                        CONSTRAINT FK_REF FOREIGN KEY (ref)
    REFERENCES tags(sno));
//////////
INSERT INTO `edamameTags`(`ref`) VALUES ((SELECT `sno` FROM `tags` WHERE name =  "allergie : soja"));
INSERT INTO `edamameTags`(`ref`) VALUES ((SELECT `sno` FROM `tags` WHERE name = "végétarien"));
INSERT INTO `edamameTags`(`ref`) VALUES ((SELECT `sno` FROM `tags` WHERE name = "végétalien"));
////////
INSERT INTO `meal_tag` VALUES ((SELECT `sno` FROM `meals` WHERE name =  "Edamame"),(SELECT `sno` FROM `tags` WHERE name =  "allergie : soja"));
INSERT INTO `meal_tag` VALUES ((SELECT `sno` FROM `meals` WHERE name =  "Edamame"),(SELECT `sno` FROM `tags` WHERE name =  "végétarien"));
INSERT INTO `meal_tag` VALUES ((SELECT `sno` FROM `meals` WHERE name =  "Edamame"),(SELECT `sno` FROM `tags` WHERE name =  "végétalien"));
INSERT INTO `meal_tag` VALUES ((SELECT `sno` FROM `meals` WHERE name =  "Wasabi Peas"),(SELECT `sno` FROM `tags` WHERE name =  "allergie : arachide"));
INSERT INTO `meal_tag` VALUES ((SELECT `sno` FROM `meals` WHERE name =  "Wasabi Peas"),(SELECT `sno` FROM `tags` WHERE name =  "allergie : gluten"));
INSERT INTO `meal_tag` VALUES ((SELECT `sno` FROM `meals` WHERE name =  "Wasabi Peas"),(SELECT `sno` FROM `tags` WHERE name =  "allergie : soja"));
INSERT INTO `meal_tag` VALUES ((SELECT `sno` FROM `meals` WHERE name =  "Wasabi Peas"),(SELECT `sno` FROM `tags` WHERE name =  "épicé"));
//////////
 public function fetchMeal($name) {

    $sql = 'SELECT * FROM meals WHERE name = :name';
    $query = $this -> conn -> prepare($sql);
    $query -> execute(array(':name' => $name));
    $data = $query -> fetchObject();
    $sno = $data -> sno;
    $meal["id"] =  $sno;
    /*
        $meal["name"] = $data -> name;
        $meal["description"] = $data -> description;
        $meal["id"] = $data -> sno;
        $meal["price"] = $data -> price;
        $meal["photo"] = $data -> photo;
        return $meal;
    * /

    $sqltags = 'SELECT * FROM tags WHERE sno = (SELECT tagID FROM meal_tag WHERE mealID = :mealID';
    $querytags = $this -> conn -> prepare($sqltags);
    $querytags -> execute(array(':mealID' => $sno));

    $tags = array();
    while($data = $querytags -> fetchObject()){
        $tags[] = $data->name;
    }

    $meal['tags'] = $tags;
    return $meal;


}


///////
    CREATE TABLE meals (
            sno int(11) NOT NULL AUTO_INCREMENT,
    unique_id varchar(23) NOT NULL,
    name varchar(50) NOT NULL,
    price decimal(6,2) NOT NULL,
    description varchar(500) NOT NULL,
    photo varchar(150) NOT NULL,
    created_at datetime DEFAULT NULL,
    PRIMARY KEY (sno)
)
*/
/*
public function askForMealsOfType($type) {

  $db = $this -> db;

  if (!empty($type)) {

    if ($db -> checkMealTypeExist($type)) {

       $result =  $db -> fetchMealsOfType($type);


       if(!$result) {

        $response["result"] = "failure";
        $response["message"] = "Invalid Meal name";
        return json_encode($response);

       } else {

        $response["result"] = "success";
        $response["message"] = "Meal successfully returned";
        $response["meal"] = $result;
        return json_encode($response);

       }

    } else {

      $response["result"] = "failure";
      $response["message"] = "Invalid Meal type";
      return json_encode($response);

    }
  } else {

      return $this -> getMsgParamNotEmpty();
    }

}


 */
    public Meal(String name, String description, /*ArrayList<String> tagList,*/String[] tagList, int totalVotes, int nbVotes, String type){
        this(0, name, null, description, tagList, totalVotes, nbVotes, type);
    }

    /*public Meal(String name, String photo, String description, ArrayList<String> tagList){
        this(new Generator().generateMealId(), name, photo, description, tagList);
    }*/

    /*

public function fetchAllMeals() {

    $sql = 'SELECT * FROM meals';
    $query = $this -> conn -> prepare($sql);
    $query -> execute(array(':type' => $type));

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

            $meals[] = $meal;
        }

        return $meals;
    }
    else{
        return false;
    }

 }
     */
    public Meal(int id, String name, String photo, String description, /*ArrayList<String> tagList,*/String[] tagList, int totalVotes, int nbVotes, String type){
        this.id=id;
        this.name=name;
        this.photo=photo;
        this.description=description;
        this.tagList=tagList;
        this.totalVotes=totalVotes;
        this.nbVotes=nbVotes;
        this.type=type;
        this.bitmap = null;

    }

    public String getName(){
        return name;
    }

    public String getPhoto(){
        return photo;
    }

    public int getId(){
        return id;
    }

    public String getType(){
        return type;
    }


    public void setBitmap(Bitmap bitmap){
        Log.d(getClass().getName(), "meal ===================" + name);
        this.bitmap =bitmap ;
    }
    public Bitmap getBitmap(){
        return bitmap;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public String[] getTagList() {
        return tagList;
    }

/*
    public ArrayList<String> getTagList() {
        //return tagList;
        return null;
    }
*/
}
/*
<?php

class DBOperations{

	 private $host = 'localhost';
	 private $user = 'id5578182_neko';
	 private $db = 'id5578182_manekinekodb';
	 private $pass = 'androideihm';
	 private $conn;

public function __construct() {

	$this -> conn = new PDO("mysql:host=".$this -> host.";dbname=".$this -> db, $this -> user, $this -> pass);

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
    $sno = $data -> sno;
    $meal["id"] =  $sno;
    /*
        $meal["name"] = $data -> name;
        $meal["description"] = $data -> description;
        $meal["id"] = $data -> sno;
        $meal["price"] = $data -> price;
        $meal["photo"] = $data -> photo;
        return $meal;
    * /

    $sqltags = 'SELECT * FROM tags WHERE sno = (SELECT tagID FROM meal_tag WHERE mealID = :mealID';
            $querytags = $this -> conn -> prepare($sqltags);
            $querytags -> execute(array(':mealID' => $sno));

            $tags = array();
            while($data = $querytags -> fetchObject()){
            $tags[] = $data->name;
            }

            $meal['tagList'] = $tags;
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
        $sqltags = 'SELECT * FROM tags WHERE sno = (SELECT tagID FROM meal_tag WHERE mealID = :mealID';
        $querytags = $this -> conn -> prepare($sqltags);
        $querytags -> execute(array(':mealID' => $meal["id"]));

        $tags = array();
        while($data = $querytags -> fetchObject()){
        $tags[] = $data->name;
        }

        $meal['tagList'] = $tags;




        $meals[] = $meal;
        }

        return $meals;
        }
        else{
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





        */