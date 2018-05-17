<?php
require_once 'DBOperations.php';
require_once 'Functions.php';
//header('Content-type: text/html; charset=UTF-8');
$db = new DBOperations();
$fc = new Functions();
//echo $fc->loginUser("hamdaniyasmine15@gmail.com", "enimsay28");
//echo $fc->getMealsOfType("boisson");
//echo $fc -> getAllMeals();
echo "Hellooo";

$tags = $db->fetchAllTags();
echo $tags;

for($i=0; $i<count($tags); $i++){
        //print_r($meal);
        echo "<br>"."tag - " . $tags[$i]."<br>";
}		


/*$response["result"] = "failure";
        $response["message"] = "Invalid Meal name";
        echo json_encode($response);

*/
//$response = $fc->fetchMealsOfType("aperitif");
//echo $response;
//echo "<br>var dump : ".var_dump($response);
//echo "error : ".json_last_error();
//mb_internal_encoding('UTF-8');
//echo 'testé';
/*
    foreach($meals as $meal){
        //print_r($meal);
        echo "<br>"."id: " . $meal['id']. " - Name: " . $meal['name']. " price: " . $meal['price'];//. " number of tags :".count($meal['tagList'])."<br>";
		/* //$tags = $meal["tagList"];
		foreach($tags as $tag){
			echo "<br> tag :". $tag;
		}*/
    //}
?>