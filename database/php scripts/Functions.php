<?php

//Base code got from https://github.com/Learn2Crack/android-login-registration-server.git 

require_once 'DBOperations.php';

class Functions{

private $db;

public function __construct() {

      $this -> db = new DBOperations();

}


public function registerUser($name, $email, $password) {

	$db = $this -> db;

	if (!empty($name) && !empty($email) && !empty($password)) {

  		if ($db -> checkUserExist($email)) {

  			$response["result"] = "failure";
  			$response["message"] = "User Already Registered !";
  			return json_encode($response);

  		} else {

  			$result = $db -> insertUsernameData($name, $email, $password);

  			if ($result) {

				  $response["result"] = "success";
  				$response["message"] = "User Registered Successfully !";
  				return json_encode($response);
  						
  			} else {

  				$response["result"] = "failure";
  				$response["message"] = "Registration Failure";
  				return json_encode($response);

  			}
  		}					
  	} else {

  		return $this -> getMsgParamNotEmpty();

  	}
}

public function loginUser($email, $password) {

  $db = $this -> db;

  if (!empty($email) && !empty($password)) {

    if ($db -> checkUserExist($email)) {

       $result =  $db -> checkLogin($email, $password);


       if(!$result) {

        $response["result"] = "failure";
        $response["message"] = "Invaild Login Credentials";
        return json_encode($response);

       } else {

        $response["result"] = "success";
        $response["message"] = "Login Successful";
        $response["user"] = $result;
        return json_encode($response);

       }

    } else {

      $response["result"] = "failure";
      $response["message"] = "Invaild Login Credentials";
      return json_encode($response);

    }
  } else {

      return $this -> getMsgParamNotEmpty();
    }

}

public function getMeal($name) {

  $db = $this -> db;

  if (!empty($name)) {

    if ($db -> checkMealExist($name)) {

       $result =  $db -> fetchMeal($name);


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
      $response["message"] = "Invalid Meal name";
      return json_encode($response);

    }
  } else {

      return $this -> getMsgParamNotEmpty();
    }

}



public function fetchMealsOfType($type) {

  $db = $this -> db;

  //echo $type;
 
  if (!empty($type)) {

    if ($db -> checkMealTypeExist($type)) {

       $result =  $db -> fetchMealsOfType($type);


       if(!$result) {

        $response["result"] = "failure";
        $response["message"] = "Invalid Meal name";
        return json_encode($response);

       } else {

        $response["result"] = "success";
        $response["message"] = "Meals successfully returned";
        $response["meals"] = $result;
		//$meals=$result;
		//echo $response;
		//return $response;
		//echo "<br>"."fc : " . $response;
		/*
        foreach($meals as $meal){
        //print_r($meal);
        echo "<br>"."id: " . $meal['id']. " - Name: " . $meal['name']. " price: " . $meal['price'];//. " number of tags :".count($meal['tagList'])."<br>";
	
		}*/
		//echo var_dump($response);
		//echo json_encode($response);
		//echo "fc done";
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


public function fetchAllMeals() {

	$db = $this -> db;

    $result =  $db -> fetchAllMeals();


       if(!$result) {

        $response["result"] = "failure";
        $response["message"] = "Invalid request";
        return json_encode($response);

       } else {

        $response["result"] = "success";
        $response["message"] = "Meals successfully returned";
        $response["meals"] = $result;
        return json_encode($response);
  }
}


public function fetchAllTags() {

	$db = $this -> db;

       $result =  $db -> fetchAllTags();


       if(!$result) {

        $response["result"] = "failure";
        $response["message"] = "Invalid request";
        return json_encode($response);

       } else {

        $response["result"] = "success";
        $response["message"] = "Tags successfully returned";
        $response["tags"] = $result;
        return json_encode($response);
  }
}


public function changePassword($email, $old_password, $new_password) {

  $db = $this -> db;

  if (!empty($email) && !empty($old_password) && !empty($new_password)) {

    if(!$db -> checkLogin($email, $old_password)){

      $response["result"] = "failure";
      $response["message"] = 'Invalid Old Password';
      return json_encode($response);

    } else {


    $result = $db -> changePassword($email, $new_password);

      if($result) {

        $response["result"] = "success";
        $response["message"] = "Password Changed Successfully";
        return json_encode($response);

      } else {

        $response["result"] = "failure";
        $response["message"] = 'Error Updating Password';
        return json_encode($response);

      }

    } 
  } else {

      return $this -> getMsgParamNotEmpty();
  }

}

public function isEmailValid($email){

  return filter_var($email, FILTER_VALIDATE_EMAIL);
}

public function getMsgParamNotEmpty(){


  $response["result"] = "failure";
  $response["message"] = "Parameters should not be empty !";
  return json_encode($response);

}

public function getMsgInvalidParam(){

  $response["result"] = "failure";
  $response["message"] = "Invalid Parameters";
  return json_encode($response);

}

public function getMsgInvalidEmail(){

  $response["result"] = "failure";
  $response["message"] = "Invalid Email";
  return json_encode($response);

}

}
