<?php

////Base code got from https://github.com/Learn2Crack/android-login-registration-server.git 

require_once 'Functions.php';

$fun = new Functions();


if ($_SERVER['REQUEST_METHOD'] == 'POST')
{
  $data = json_decode(file_get_contents("php://input"));

  if(isset($data -> operation)){

  	$operation = $data -> operation;

  	if(!empty($operation)){

  		if($operation == 'register'){

  			if(isset($data -> user ) && !empty($data -> user) && isset($data -> user -> name) 
  				&& isset($data -> user -> email) && isset($data -> user -> password)){

  				$user = $data -> user;
  				$name = $user -> name;
  				$email = $user -> email;
  				$password = $user -> password;

          if ($fun -> isEmailValid($email)) {
            
            echo $fun -> registerUser($name, $email, $password);

          } else {

            echo $fun -> getMsgInvalidEmail();
          }

  			} else {

  				echo $fun -> getMsgInvalidParam();

  			}

  		}else if ($operation == 'login') {

        if(isset($data -> user ) && !empty($data -> user) && isset($data -> user -> email) && isset($data -> user -> password)){

          $user = $data -> user;
          $email = $user -> email;
          $password = $user -> password;

          echo $fun -> loginUser($email, $password);

        } else {

          echo $fun -> getMsgInvalidParam();

        }
      } else if ($operation == 'chgPass') {

        if(isset($data -> user ) && !empty($data -> user) && isset($data -> user -> email) && isset($data -> user -> old_password) 
          && isset($data -> user -> new_password)){

          $user = $data -> user;
          $email = $user -> email;
          $old_password = $user -> old_password;
          $new_password = $user -> new_password;

          echo $fun -> changePassword($email, $old_password, $new_password);

        } else {

          echo $fun -> getMsgInvalidParam();

        }
      }
      else if ($operation == 'fetchMeals') {

          echo $fun -> getAllMeals();

        } else if ($operation == 'getMeal') {

        if(isset($data -> name)){

          $name = $data -> name;
          

          echo $fun -> getMeal($name);

        } else {

          echo $fun -> getMsgInvalidParam();

        }
            
        }

  	}else{

  		
  		echo $fun -> getMsgParamNotEmpty();

  	}
  } else {

  		echo $fun -> getMsgInvalidParam();

  }
} else if ($_SERVER['REQUEST_METHOD'] == 'GET'){

    //echo $fun -> getMsgParamNotEmpty();
     if(isset($_GET['method'])){
         $method = $_GET['method'];
         
		 /*$response["result"] = "success";
            $response["message"] = $type;
            echo json_encode($response);*/
            
        
            if($method=='fetchMeals'){
                echo $fun -> fetchAllMeals();
            }else{
            if($method == 'fetchMealsOfType'){
                $type =  $_GET['type'];
				//echo $type;
                echo $fun -> fetchMealsOfType($type);
            }
            else if($method == 'fetchAllTags'){
                
                echo $fun -> fetchAllTags();
                
            } else{
  		echo $fun -> getMsgInvalidParam();
            }
			}
        }
    
}
/*

     if(isset($_GET['method'])=='fetchMeals'){
            echo $fun -> getAllMeals();
        }
        else
        if(isset($_GET['method']) == 'fetchMealsOfType'){
            echo $fun -> getMealsOfType("boisson");
                
        }
        
        else {
  		    echo $fun -> getMsgInvalidParam();
        
        }
           
    }

*/









        /*
        if(isset($_GET['type'])){

          //$type = $_GET['type'];
          
          echo $fun -> getMealsOfType("boisson");

        } else {

          echo $fun -> getMsgInvalidParam();

        }
    }else {

  		echo $fun -> getMsgInvalidParam();

    }
    /*
    $data = json_decode(file_get_contents("php://input"));
  
    if(isset($data -> operation)){

  	$operation = $data -> operation;

  	if(!empty($operation)){
  	    if ($operation == 'fetchMeals') {

          echo $fun -> getAllMeals();
        }
  	}else{

  		
  		echo $fun -> getMsgParamNotEmpty();
    }
        
    } else {

  		echo $fun -> getMsgInvalidParam();

  }
  */
  //echo "Learn2Crack Login API";



