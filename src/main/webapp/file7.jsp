<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Login</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
  <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@600&display=swap" rel="stylesheet">
<style>
    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
      font-family: "Poppins", sans-serif;
    }

    body {
      display: flex;
      justify-content: center;
      align-items: center;
      min-height: 100vh;
      background: url('img1.jpg') no-repeat;
      background-size: cover;
      background-position: center;
    }

    .wrapper {
      width: 420px;
      background: transparent;
      border: 2px solid rgba(255, 255, 255, .2);
      color: #fff;
      border-radius: 10px;
      padding: 30px 40px;
    }

    .wrapper h1 {
    margin-bottom: 16px;
      font-size: 36px;
      text-align: center;
    }

    .wrapper .input-box {
      position: relative;
      width: 100%;
      height: 50px;
      margin: 30px 0;
    }

    .input-box input {
      width: 100%;
      height: 100%;
      background: transparent;
      border: none;
      outline: none;
      border: 2px solid rgba(255, 255, 255, .2);
      border-radius: 40px;
      font-size: 16px;
      color: #fff;
      padding: 20px 45px 20px 20px;
    }

    .input-box input::placeholder {
      color: #fff;
    }

    .input-box input::placeholder {
      color: #fff;
    }

    .input-box i {
      position: absolute;
      right: 20px;
      top: 30%;
      transform: translate(-50%);
      font-size: 20px;
    }

    .wrapper .remember-forgot {
      display: flex;
      justify-content: space-between;
      font-size: 14.5px;
      margin: -15px 0 15px;
    }

    .remeber-forgot label input {
      accent-color: #fff;
      margin-right: 3px;
    }

    .remember-forgot a {
      color: #fff;
      text-decoration: none;
    }

    .remember-forgot a:hover {
      text-decoration: underline;
    }

    .wrapper .btn {
        margin-bottom: 18px;
      width: 100%;
      height: 45px;
      background: #fff;
      border: none;
      outline: none;
      border-radius: 40px;
      box-shadow: 0 0 10px rgba(0, 0, 0, .1);
      cursor: pointer;
      font-size: 16px;
      color: #333;
      font-weight: 600;
    }
    
    .wrapper .btn1 {
      width: 100%;
      height: 45px;
      background: #fff;
      border: none;
      outline: none;
      border-radius: 40px;
      box-shadow: 0 0 10px rgba(0, 0, 0, .1);
      cursor: pointer;
      font-size: 16px;
      color: #333;
      font-weight: 600;
    }

    .wrapper .register-link {
      font-size: 14.5px;
      text-align: center;
      margin: 20px 0 15px;
    }

    .register-link p a {
      color: #fff;
      text-decoration: none;
      font-weight: 600;
    }

    .register-link p a:hover {
      text-decoration: underline;
    }

    .btn p a {
      color: #070101;
      text-decoration: none;
      font-weight: 600;
    }

    .btn p a:hover {
      text-decoration: underline;
    }
    
    .btn1 p a {
      color: #070101;
      text-decoration: none;
      font-weight: 600;
    }

    .btn1 p a:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
<div class="wrapper">
<%
String quiz_name = (String) session.getAttribute("quiz_name");
String username = (String) session.getAttribute("username");

%>
<form action='username_list'>
<h1>Analysis</h1>
	<input type='hidden' name='change' value='1'>
	<input class="btn" type='submit' value='Usernamewise'>
</form>
<form action='quizList3'>
	<input class="btn1" type='submit' value='Average'>
</form>
</div>
</body>
</html>
