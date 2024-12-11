<%@ page import="com.code.Task" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <link rel="icon" href="https://img.icons8.com/office/40/bee.png" type="image/png">
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #ffffff;
            color: #000000;
        }

        .navbar {
            width: 250px;
            background-color: #fff700;
            height: 100vh;
            position: fixed;
            display: flex;
            flex-direction: column;
            align-items: center;
            padding-top: 20px;
            border-right: 1px solid #d3d3d3;
        }

        .profile-img {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            margin-bottom: 10px;
        }

        .user-name {
            font-size: 1.2rem;
            margin-bottom: 20px;
            color: #000000;
        }

        .nav-links {
            list-style: none;
            padding: 0;
            width: 100%;
        }

        .nav-links li {
            width: 100%;
            text-align: center;
            padding: 15px 0;
            cursor: pointer;
            transition: background-color 0.3s;
            color: #000000;
        }

        .nav-links li:hover {
            background-color: #d3d3d3;
        }

        .logout {
            margin-top: auto;
            margin-bottom: 20px;
            padding: 10px 20px;
            background-color: #fff700;
            color: #000000;
            border: none;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .logout:hover {
            background-color: #ff0000;
        }

        .content {
            margin-left: 250px;
            padding: 20px;
            background-color: #ffffff;
            color: #000000;
            height: 100vh;
            overflow-y: auto;
            box-sizing: border-box;
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center center;
        }

        .section {
            display: none;
        }

        .section.active {
            display: block;
        }

        .nav-links li img {
            vertical-align: middle;
            margin-right: 10px;
        }

        .logout img {
            vertical-align: middle;
            margin-right: 10px;
        }
    </style>
</head>
<body>
<div class="navbar">
    <img src="<%= (request.getAttribute("profileImage") != null) ? request.getAttribute("profileImage") : "https://cdn-icons-png.freepik.com/512/17740/17740782.png" %>"
         alt="User Profile" class="profile-img">
    <div class="user-name">
        <%= (request.getAttribute("userName") != null) ? request.getAttribute("userName") : "" %>
    </div>
    <ul class="nav-links">
        <li onclick="showSection('my-day')"><img width="28" height="28" src="https://img.icons8.com/fluency/48/sun.png" alt="sun"/> My Day</li>
        <li onclick="showSection('important')"><img width="28" height="28" src="https://img.icons8.com/nolan/50/star.png" alt="star"/> Important</li>
        <li onclick="showSection('all')"><img width="28" height="28" src="https://img.icons8.com/dusk/50/infinity.png" alt="infinity"/> All</li>
        <li onclick="showSection('completed')"><img width="28" height="28" src="https://img.icons8.com/fluency/48/checked.png" alt="checked"/> Completed</li>
        <li onclick="showSection('settings')"><img width="28" height="28" src="https://img.icons8.com/nolan/50/settings.png" alt="settings"/> Settings</li>
    </ul>

    <a href="login.jsp" class="logout" style="text-decoration: none; border-radius: 10px; display: flex; align-items: center;">
        <img width="28" height="28" src="https://img.icons8.com/fluency/48/exit--v1.png" alt="exit--v1"/>
        Logout
    </a>
</div>

<div class="content" id="content">
    <% String message = (String) request.getAttribute("message"); %>
    <% if (message != null) { %>
    <div style="color: green; font-weight: bold; margin: 10px 0;">
        <%= message %>
    </div>
    <% } %>

    <div id="my-day" class="section active" style="max-width: 600px; margin: 20px auto; padding: 20px; background-color: #ffffff; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); border-radius: 8px; border: 2px;">
        <h1 style="font-size: 24px; color: #4CAF50; text-align: center;">My Day</h1>
        <h6 id="current-date-my-day" style="font-size: 14px; color: #555; text-align: center; margin-bottom: 20px;"></h6>
        <script>
            document.getElementById("current-date-my-day").innerText = new Date().toLocaleDateString();
        </script>
        <div>
            <form action="todo" method="post" style="display: flex; flex-direction: column; gap: 10px;">
                <input type="hidden" name="action" value="add">
                <input type="text" name="to_do" placeholder="Add a Task" required style="padding: 10px;">
                <input type="text" name="description" placeholder="Add Description" required style="padding: 10px;">
                <input type="date" name="due_date" required style="padding: 10px;">
                <input type="time" name="due_time" required style="padding: 10px;">
                <div style="display: flex; align-items: center;">
                    <label style="font-size: 14px;">Do you want to get a reminder through email?</label>
                    <input type="checkbox" name="send_mail" value="true" style="margin-left: 10px;">
                </div>
                <input type="hidden" name="important" value="false">
                <button
                        type="submit"
                        style="padding: 10px; background-color: #4CAF50; color: white; border: none; transition: background-color 0.3s, transform 0.3s;"
                        onmouseover="this.style.backgroundColor='#45a049'; this.style.transform='scale(1.05)';"
                        onmouseout="this.style.backgroundColor='#4CAF50'; this.style.transform='scale(1)';">
                    Add Task
                </button>
            </form>
        </div>
    </div>

    <div id="important" class="section" style="max-width: 600px; margin: 20px auto; padding: 20px; background-color: #ffffff; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); border-radius: 8px;">
        <h1 style="font-size: 24px; color: #4CAF50; text-align: center;">Important</h1>
        <h6 id="current-date-important" style="font-size: 14px; color: #555; text-align: center; margin-bottom: 20px;"></h6>
        <script>
            document.getElementById("current-date-important").innerText = new Date().toLocaleDateString();
        </script>
        <div>
            <form action="todo" method="post" style="display: flex; flex-direction: column; gap: 10px;">
                <input type="hidden" name="action" value="add">
                <input type="text" name="to_do" placeholder="Add a Task" required style="padding: 10px;">
                <input type="text" name="description" placeholder="Add Description" required style="padding: 10px;">
                <input type="date" name="due_date" required style="padding: 10px;">
                <input type="time" name="due_time" required style="padding: 10px;">
                <div style="display: flex; align-items: center;">
                    <label style="font-size: 14px;">Do you want to get a reminder through email?</label>
                    <input type="checkbox" name="send_mail" value="true" style="margin-left: 10px;">
                </div>
                <input type="hidden" name="important" value="true">
                <button
                        type="submit"
                        style="padding: 10px; background-color: #4CAF50; color: white; border: none; transition: background-color 0.3s, transform 0.3s;"
                        onmouseover="this.style.backgroundColor='#45a049'; this.style.transform='scale(1.05)';"
                        onmouseout="this.style.backgroundColor='#4CAF50'; this.style.transform='scale(1)';">
                    Add Task
                </button>
            </form>
        </div>
    </div>

        <div id="all" class="section" style="padding: 20px; background-color: #f4f4f9; border-radius: 8px; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);">
            <div style="background-color: #ffffff; padding: 20px; border-radius: 8px;">
                <h3 style="text-align: center; color: #333; font-size: 24px; margin-bottom: 20px;">All Tasks</h3>
                <table style="width: 100%; border-collapse: collapse; margin: 0 auto; text-align: left; font-family: Arial, sans-serif;">
                    <thead>
                    <tr style="background-color: purple; color: #fff; font-weight: bold;">
                        <th style="padding: 10px 15px; border: 1px solid #ddd;">Task</th>
                        <th style="padding: 10px 15px; border: 1px solid #ddd;">Description</th>
                        <th style="padding: 10px 15px; border: 1px solid #ddd;">Due Date</th>
                        <th style="padding: 10px 15px; border: 1px solid #ddd;">Due Time</th>
                        <th style="padding: 10px 15px; border: 1px solid #ddd;">Email Notification</th>
                        <th style="padding: 10px 15px; border: 1px solid #ddd;">Important</th>
                        <th style="padding: 10px 15px; border: 1px solid #ddd;">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="task" items="${allTasks}">
                        <tr style="background-color: #f9f9f9;">
                            <td style="padding: 10px 15px; border: 1px solid #ddd;">${task.toDo}</td>
                            <td style="padding: 10px 15px; border: 1px solid #ddd;">${task.description}</td>
                            <td style="padding: 10px 15px; border: 1px solid #ddd;">${task.dueDate}</td>
                            <td style="padding: 10px 15px; border: 1px solid #ddd;">${task.dueTime}</td>
                            <td style="padding: 10px 15px; border: 1px solid #ddd;">${task.sendMail ? 'Yes' : 'No'}</td>
                            <td style="padding: 10px 15px; border: 1px solid #ddd;">${task.important ? 'Yes' : 'No'}</td>
                            <td style="padding: 10px 15px; border: 1px solid #ddd; text-align: center;">
                                <button onclick="editTask(${task.id})" style="padding: 5px 10px; background-color: #4CAF50; color: white; border: none; cursor: pointer; border-radius: 5px; margin-right: 5px; transition: background-color 0.3s;">
                                    Edit
                                </button>
                                <button onclick="deleteTask(${task.id})" style="padding: 5px 10px; background-color: #f44336; color: white; border: none; cursor: pointer; border-radius: 5px; transition: background-color 0.3s;">
                                    Delete
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <script>
            function editTask(taskId) {
                alert("Edit Task " + taskId);
            }

            function deleteTask(taskId) {
                if (confirm("Are you sure you want to delete this task?")) {
                    alert("Task " + taskId + " deleted");
                }
            }
        </script>

        <div id="completed" class="section" style="padding: 20px; background-color: #f4f4f9; border-radius: 8px; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); margin-top: 20px;">
            <h3 style="text-align: center; color: #333; font-size: 24px; margin-bottom: 20px;">Completed Tasks</h3>
            <table style="width: 100%; border-collapse: collapse; margin: 0 auto; text-align: left; font-family: Arial, sans-serif;">
                <thead>
                <tr style="background-color: #28a745; color: #fff; font-weight: bold;">
                    <th style="padding: 10px 15px; border: 1px solid #ddd;">Task</th>
                    <th style="padding: 10px 15px; border: 1px solid #ddd;">Description</th>
                    <th style="padding: 10px 15px; border: 1px solid #ddd;">Due Date</th>
                    <th style="padding: 10px 15px; border: 1px solid #ddd;">Due Time</th>
                    <th style="padding: 10px 15px; border: 1px solid #ddd;">Email Notification</th>
                    <th style="padding: 10px 15px; border: 1px solid #ddd;">Important</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="task" items="${completedTasks}">
                    <tr style="background-color: #f9f9f9;">
                        <td style="padding: 10px 15px; border: 1px solid #ddd;">${task.toDo}</td>
                        <td style="padding: 10px 15px; border: 1px solid #ddd;">${task.description}</td>
                        <td style="padding: 10px 15px; border: 1px solid #ddd;">${task.dueDate}</td>
                        <td style="padding: 10px 15px; border: 1px solid #ddd;">${task.dueTime}</td>
                        <td style="padding: 10px 15px; border: 1px solid #ddd;">${task.sendMail ? 'Yes' : 'No'}</td>
                        <td style="padding: 10px 15px; border: 1px solid #ddd;">${task.important ? 'Yes' : 'No'}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div id="settings" class="section" style="max-width: 700px; margin: 20px auto; padding: 20px; background-color: #ffffff; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); border-radius: 8px;">
            <h1 style="font-size: 24px; color: #4CAF50; text-align: center;">Settings</h1>

            <form action="updateProfile" method="post" enctype="multipart/form-data" style="display: flex; flex-direction: column; gap: 20px;">
                <% String userName = (String) request.getAttribute("userName"); %>
                <% String email = (String) request.getAttribute("userEmail"); %>
                <% String profileImage = (String) request.getAttribute("profileImage"); %>

                <label for="name" style="font-size: 14px; color: #555;">Name:</label>
                <input type="text" name="name" id="name" value="<%= (userName != null) ? userName : "" %>" placeholder="Enter your name" required style="padding: 10px; border-radius: 5px; border: 1px solid #ccc;">

                <label for="email" style="font-size: 14px; color: #555;">Email:</label>
                <input type="email" name="email" id="email" value="<%= (email != null) ? email : "" %>" placeholder="Enter your email" required style="padding: 10px; border-radius: 5px; border: 1px solid #ccc;">

                <label for="image" style="font-size: 14px; color: #555;">Profile Image:</label>
                <input type="file" name="image" id="image" accept="image/*" style="padding: 10px; border-radius: 5px; border: 1px solid #ccc;">

                <div style="text-align: center; margin: 20px 0;">
                    <% if (profileImage != null) { %>
                    <img src="<%= profileImage %>" alt="Profile Image" style="width: 100px; height: 100px; border-radius: 50%; margin-bottom: 10px;">
                    <% } else { %>
                    <img src="https://cdn-icons-png.freepik.com/512/17740/17740782.png" alt="Default Profile Image" style="width: 100px; height: 100px; border-radius: 50%; margin-bottom: 10px;">
                    <% } %>
                </div>

                <button type="submit" style="padding: 12px; background-color: #4CAF50; color: white; border: none; border-radius: 5px; cursor: pointer; transition: background-color 0.3s;">
                    Update Profile
                </button>
            </form>
        </div>
</div>

<script>
    const wallpapers = [
        "https://images.unsplash.com/photo-1502790671504-542ad42d5189?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        "https://images.unsplash.com/photo-1487621167305-5d248087c724?q=80&w=2532&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        "https://images.unsplash.com/photo-1511300636408-a63a89df3482?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        "https://images.unsplash.com/photo-1498588543704-e0d466ddcfe5?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        "https://images.unsplash.com/photo-1473654729523-203e25dfda10?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        "https://images.unsplash.com/photo-1494564605686-2e931f77a8e2?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        "https://images.unsplash.com/photo-1508739773434-c26b3d09e071?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        "https://images.unsplash.com/photo-1508182314998-3bd49473002f?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        "https://images.unsplash.com/photo-1451337516015-6b6e9a44a8a3?q=80&w=2574&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        "https://images.unsplash.com/photo-1505832018823-50331d70d237?q=80&w=2708&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        "https://images.unsplash.com/photo-1589490047559-a1c13ec25b87?q=80&w=2574&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        "https://images.unsplash.com/photo-1609611603740-18b22e96a121?q=80&w=2688&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
    ];


    window.onload = function() {
        const randomIndex = Math.floor(Math.random() * wallpapers.length);
        document.getElementById("content").style.backgroundImage = `url('${wallpapers[randomIndex]}')`;
    };

    function showSection(sectionId) {
        const sections = document.querySelectorAll('.section');
        sections.forEach(section => section.classList.remove('active'));
        document.getElementById(sectionId).classList.add('active');
    }
</script>
</body>
</html>
