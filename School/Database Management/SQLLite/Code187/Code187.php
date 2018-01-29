<!DOCTYPE html>
<link rel="stylesheet" href="code187.css" type="text/css">
<head xmlns="http://www.w3.org/1999/html">
    <title>Code 187 Database</title>
    <meta http-equiv="Content-Type" content="text/html
                    charset=UTF-8"/>
    <meta name="Author" content="Bryan Garcia"/>
    <meta name="description" content="Code 187's Serial Killer Database"/>
</head>
<body>
<h1><a class="title" href="/Code187/Code187.php">Code 187 Database</a></h1>

<!--<li><a href="/Code187/class.php">Restart HERE</a></li>-->
<B>Team Members:</B>
<ul>
    <li>Bedrya Balema</li>
    <li>Bryan Garcia</li>
    <li>Justin Passanisi</li>
    <li>Colt Wilson</li>
</ul>


<hr>
<B>Relations:</B>
<ol>
    <li><a href="/Code187/Code187.php?query=qCountry">Country</a></li>
    <li><a href="/Code187/Code187.php?query=qState">State</a></li>
    <li><a href="/Code187/Code187.php?query=qContain">Contain</a></li>
    <li><a href="/Code187/Code187.php?query=qSerialKiller">SerialKiller</a></li>
    <li><a href="/Code187/Code187.php?query=qPerson">Person</a></li>
    <li><a href="/Code187/Code187.php?query=qKills">Kills</a></li>
    <li><a href="/Code187/Code187.php?query=qKillsIn">KillsIn</a></li>
    <li><a href="/Code187/Code187.php?query=qTrial">Trial</a></li>
    <li><a href="/Code187/Code187.php?query=qGoesTo">Goes To</a></li>
    <li><a href="/Code187/Code187.php?query=qVictim">Victim</a></li>
</ol>

<hr>
<B>Queries:</B>
<ul>
    <li><a href="/Code187/Code187.php?query=q1">Query 1</a>&nbsp;- Find ALL serial killers who gender is female</li>
    <li><a href="/Code187/Code187.php?query=q2">Query 2</a>&nbsp;- Find the most populated country</li>
    <li><a href="/Code187/Code187.php?query=q3">Query 3</a>&nbsp;- Serial killer’s victim count (descending order)</li>
    <li><a href="/Code187/Code187.php?query=q4">Query 4</a>&nbsp;- Total number of people that went to trial</li>
    <li><a href="/Code187/Code187.php?query=q5">Query 5</a>&nbsp;- Victims in California from 2000 or before</li>
</ul>

<hr>
<b>Insert a Serial Killer</b>
<b> </b>
<Form method="get">
    Serial Killer Name: <input name="nameBox" id="skName" placeholder="Full Name" type="text"/>
    <br>
    Serial Killer Birthday: <input name="bdayBox" id="skBirthdate" placeholder="YYYY-MM-DD" type="text"/>
    <br>
    Serial Killer Ethnicity: <input name="ethnicityBox" id="skEth" placeholder="ethncity" type="text"/>
    <br>
    Serial Killer Gender (M, F, O, Unknown): <input name="genderBox" id="skGender" placeholder="M, F, O,  or Unknown"
                                                    type="text"/>
    <br>
    Serial Killer Current Status (NotCaught, Dead, Caught, or Unknown): <input name="currentStatusBox" id="currStat"
                                                                               placeholder="e.g. Dead" type="text"/>
    <br>
    <input type="submit" name1="nameBox"
           name2="bdayBox" name3="currentStatusBox"
           name4="genderBox" name5="ethnicityBox"
           value="Add Serial Killer">
</Form>

<?php
if (isset($_GET['nameBox']) &&
    isset($_GET['bdayBox']) &&
    isset($_GET['currentStatusBox']) &&
    isset($_GET['genderBox']) &&
    isset($_GET['ethnicityBox'])
) {

    $name = $_GET['nameBox'];
    $bday = $_GET['bdayBox'];
    $currStat = $_GET['currentStatusBox'];
    $ethnicity = $_GET['ethnicityBox'];
    $gender = $_GET['genderBox'];

    if ($bday == "") {
        echo "**ERROR! Fill out all information!**";
    } elseif (check($bday)) {

        $servername = "localhost";
        $username = "root";
        $password = "";
        $dbname = "157A_Database";

        // Create connection
        $conn = new mysqli($servername, $username, $password, $dbname);

        // Check connection
        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }
        $insertSKQuery = "INSERT INTO Person (name, dateOfBirth, race, gender) VALUES ('$name', '$bday','$ethnicity', '$gender'); 
          INSERT INTO SerialKiller (name, dateOfBirth, currentStatus) VALUES ('$name', '$bday', '$currStat'); ";

//        $result = $conn->query($insertSKQuery);

        if ($conn->multi_query($insertSKQuery) === TRUE) {
            echo "Added Serial Killer successfully";
        } else {
            echo "Error: " . $insertSKQuery . "<br>" . $conn->error;
        }
        $conn->close();
    } else {
        echo "Bad date format!";
    }


} else {
//    echo "Try to add a serial killer!";
}

function check($checkBday)
{
    // YYYY-MM-DD
    //cut up the string
    $year = intval(substr($checkBday, 0, 3));
    $day = intval(substr($checkBday, 8, 9));
    $month = intval(substr($checkBday, 5, 6));

    if (checkdate($month, $day, $year)) {
        return true;
    } else {
        return false;
    }
}

?>

<hr>
<b>Do you share your name with a Serial Killer?</b>
<b> </b>
<Form method="get">
    Your Name: <input name="nameBox" id="userName" placeholder="e.g. Roy or Ted" type="text"/>
    <br>
    <input type="submit" name1="nameBox" value="Check">
</Form>
<?php
if (isset($_GET['nameBox'])){
    $user = $_GET['nameBox'];

    $servername = "localhost";
    $username = "root";
    $password = "";
    $dbname = "157A_Database";

    $query = "SELECT name FROM serialKiller WHERE name LIKE '%$user%'";

    // Create connection
    $conn = new mysqli($servername, $username, $password, $dbname);

    // Check connection
    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }

    $result = $conn->query($query);

    if (($result != null) && $result->num_rows > 0) {
        // output data of each row
        echo "<table id='table' class='resultsTable' ><tr>";

        while ($row = $result->fetch_assoc()) {

            foreach ($row as $key => $var) {
                //Each row here
//                    echo $key . ' = ' . $var . '<br>';
                echo "<th>" . $key . "</th>";
            }

            echo "<tr>";

            foreach ($row as $key => $var) {
                echo "<td align='center'>" . $var . "</td>";
            }
            break;

        }
        while ($row = $result->fetch_assoc()) {
            echo "<tr>";

            foreach ($row as $key => $var) {
                echo "<td align='center'>" . $var . "</td>";
            }


        }
        echo "</table>";
    } else {
        echo "No serial killer matches $user!";
    }
    $conn->close();


}
?>

<hr>
<B>Ad-hoc Query:</B>
<FORM METHOD=GET ACTION="">
    <table class="adHocTable">
        <tr>
            <td align=left>
                <strong>Please enter your query here<br></strong>
                (We support Select, Insert, <br>Update and Delete queries)
            </td>
            <td>
                <textarea rows="10" cols="50" name="query"></textarea>
            </td>
        </tr>
        <tr align=right>
            <td>
            </td>
            <td>
                <input type=reset value="Clear">
                <input type=submit value="Submit" name="btnQuery">
            </td>
    </table>
</FORM>

<div id="results">
    <hr>
    <b>Query Results</b>
    <br>
    <?php

    if (!(isset($_REQUEST['query']))) {
        print "We have no query yet!";
    } else {

        $qCheck = $_REQUEST['query'];

        if ($qCheck[0] == 'q') {
            $q = substr($qCheck, 1);

            if ($q == "1") {
                $query = "SELECT person.name FROM serialkiller, person WHERE serialkiller.name = person.name AND person.gender = 'F'";
            } elseif ($q == "2") {
                $query = "SELECT name, population FROM Country WHERE population >=  (select max(population) from Country)";
            } elseif ($q == "3") {
                $query = "SELECT skname, COUNT(*) FROM kills GROUP BY skname ORDER BY COUNT(*) DESC;";
            } elseif ($q == "4") {
                $query = "SELECT COUNT(DISTINCT (personName)) FROM goesTo";
            } elseif ($q == "5") {
                $query = "SELECT * FROM serialkiller, victim, kills, killsin, state WHERE serialkiller.name = kills.skName AND serialkiller.dateOfBirth = kills.skDateOfBirth AND victim.name = kills.victimName AND Year(victim.dateOfDeath) <= 2000 AND serialkiller.name = killsin.personName AND state.name = killsin.stateName AND state.name = 'California';";
            } else {
                $query = "SELECT * FROM " . $q;
            }
        } else {
            $query = $_REQUEST['query'];
        }
        //For debugging
//            echo $query . '<br><br>';


        $servername = "localhost";
        $username = "root";
        $password = "";
        $dbname = "157A_Database";

        // Create connection
        $conn = new mysqli($servername, $username, $password, $dbname);

        // Check connection
        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }

        $result = $conn->query($query);

        /*
         * You will also need to make sure that all query results
         * have column names neatly presented and that data is clearly tabulated.
         */
        if (($result != null) && $result->num_rows > 0) {
            // output data of each row
            echo "<table id='table' class='resultsTable' ><tr>";

            while ($row = $result->fetch_assoc()) {

                foreach ($row as $key => $var) {
                    //Each row here
//                    echo $key . ' = ' . $var . '<br>';
                    echo "<th>" . $key . "</th>";
                }

                echo "<tr>";

                foreach ($row as $key => $var) {
                    echo "<td align='center'>" . $var . "</td>";
                }
                break;

            }
            while ($row = $result->fetch_assoc()) {
                echo "<tr>";

                foreach ($row as $key => $var) {
                    echo "<td align='center'>" . $var . "</td>";
                }


            }
            echo "</table>";
        } else {
            echo "No results in Code 187 Database!";
        }
        $conn->close();
    }


    ?>
    <br>
    <br>
</div>
</body>
