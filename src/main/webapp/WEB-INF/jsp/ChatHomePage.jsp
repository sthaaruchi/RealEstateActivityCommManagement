<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">
<head>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>Real Estates Chatroom</title>
</head>
<body>
<br>

<h1>Real Estate Chatroom</h1>
&nbsp; <a href="/home">Home</a>
<br>

<br>
<div id="userJoin" class="container">

    <form id="userJoinForm" name="userJoinForm">
        <div class="form-group">
            <label for="name">Enter Name:</label>
            <input type="text" class="form-control" id="name" aria-describedby="name" placeholder="Enter name">
        </div>
        <div class="form-group">
            <label for="room">Enter Room:</label>
            <select id="room" type="text" aria-describedby="exampleInputRoom" placeholder="Enter room">
  <option value="juristics">Juristic</option>
  <option value="technicians">Technicians</option>
  <option value="securities">Securities</option>
  <option value="residents">Residents</option>
</select>
            <!-- <type="text" class="form-control" id="room" aria-describedby="exampleInputRoom" placeholder="Enter room"> -->
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>

</div>

<div id="chatPage" class="container d-none">
    <div class="chat-header">
        <h2>Chatroom [<span id="room-id-display"></span>]</h2>
    </div>
<!--     <div class="waiting">
        We are waiting to enter the room.
    </div> -->
    <div class="card">
        <div class="card-body">
    <ul id="messageArea">
    </div>
    </div>


    </ul>
    <form id="messagebox" name="messagebox">
        <div class="form-group">
            <label for="message">Enter Message:</label>
            <input type="text" class="form-control" id="message" aria-describedby="name" placeholder="Enter message to chat ....">
        </div>
        <button type="submit" class="btn btn-primary">Send</button> &nbsp; &nbsp;
        <a href="/chatroom" class="btn btn-primary">Back</a>
    </form>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.3.0/sockjs.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>
<script src="https://cdn.jsdelivr.net/npm/js-cookie@2/src/js.cookie.min.js"></script>
<script src="/js/mychat.js"></script>
</body>
</html>