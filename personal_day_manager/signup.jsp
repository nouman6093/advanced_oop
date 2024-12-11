<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sign Up</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="icon" href="https://img.icons8.com/office/40/bee.png" type="image/png">
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary" style= "background-color: beige">
    <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp">
            <img src="assets/logo.png" alt="Taskly Logo"  style="width: 100px; height: auto;">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="index.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="about.jsp">About</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="contact.jsp">Contact</a>
                </li>
                <li class="nav-item">
                    <div style="background-color: yellow; border-radius: 10px">
                        <a class="nav-link active" aria-current="page" href="signup.jsp">Get Started</a>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container" style="display: grid; grid-template-columns: 1fr 1fr; gap: 20px; padding: 30px; margin: 50px auto; background: #fff; border-radius: 10px; box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1); max-width: 900px;">
    <div style="display: flex; flex-direction: column; align-items: center; justify-content: center; padding: 20px;">
        <%
            String message = (String) request.getAttribute("message");
        %>
        <% if (message != null) { %>
        <p style="color: black; font-size: 1rem; margin-bottom: 15px;">
            <%= message %>
        </p>
        <% } else { %>
        <p style="color: black; font-size: 1rem; margin-bottom: 15px;">
            <!-- No message to display -->
        </p>
        <% } %>

        <h2 style="color: #007bff; margin-bottom: 20px;">Signup</h2>
        <form action="SignupServlet" method="post" style="display: flex; flex-direction: column; gap: 15px; width: 100%; max-width: 300px;">
            <input type="text" name="username" placeholder="Enter username" required style="padding: 12px; border: 1px solid #ccc; border-radius: 5px; font-size: 1rem; width: 100%;">
            <input type="password" name="password" placeholder="Enter password" required style="padding: 12px; border: 1px solid #ccc; border-radius: 5px; font-size: 1rem; width: 100%;">
            <button type="submit"
                    style="
        padding: 12px;
        background-color: yellow; /* Exact yellow */
        color: black;
        border: none;
        border-radius: 5px;
        font-size: 1rem;
        cursor: pointer;
        transition: background-color 0.3s;
    "
                    onmouseover="this.style.backgroundColor='#FFC700'"
                    onmouseout="this.style.backgroundColor='#FFD700'">
                Signup
            </button>
        </form>
        <div class="message" style="margin-top: 15px; font-size: 0.9rem;">
            Already have an account? <a href="login.jsp" style="color: #007bff; text-decoration: none;">Login</a>
        </div>
    </div>
    <div style="padding: 20px; display: flex; align-items: center; justify-content: center;">
        <img src="https://img.freepik.com/free-vector/team-checklist-concept-illustration_114360-13202.jpg?t=st=1732615333~exp=1732618933~hmac=5d736d887d746eda1585230fa1d66a04669b701f0a2b9ed9ee24f0890fed483a&w=1480" style="width: 100%; max-width: 400px; border-radius: 10px; box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);">
    </div>
</div>

<footer class="text-center text-lg-start bg-body-tertiary text-muted">
    <section class="d-flex justify-content-center justify-content-lg-between p-4 border-bottom">
        <div class="me-5 d-none d-lg-block">
            <span>We have been building Taskify with Passion. Rest assured that we will never sell out to the highest bidder.</span>
        </div>

        <div>
            <a href="" class="me-4 text-reset">
                <i class="fab fa-facebook-f"></i>
            </a>
            <a href="" class="me-4 text-reset">
                <i class="fab fa-twitter"></i>
            </a>
            <a href="" class="me-4 text-reset">
                <i class="fab fa-google"></i>
            </a>
            <a href="" class="me-4 text-reset">
                <i class="fab fa-instagram"></i>
            </a>
            <a href="" class="me-4 text-reset">
                <i class="fab fa-linkedin"></i>
            </a>
            <a href="" class="me-4 text-reset">
                <i class="fab fa-github"></i>
            </a>
        </div>
    </section>

    <section class="">
        <div class="container text-center text-md-start mt-5">
            <div class="row mt-3">
                <div class="col-md-3 col-lg-4 col-xl-3 mx-auto mb-4">
                    <h6 class="text-uppercase fw-bold mb-4">
                        <i class="fas fa-gem me-3"></i>Taskify
                    </h6>
                    <p>
                        Join mutual fellas who organize work and life with Taskify.
                    </p>
                </div>

                <div class="col-md-3 col-lg-2 col-xl-2 mx-auto mb-4">
                    <h6 class="text-uppercase fw-bold mb-4">
                        Resources
                    </h6>
                    <p>
                        <a href="about.jsp" class="text-reset">About</a>
                    </p>
                    <p>
                        <a href="contact.jsp" class="text-reset">Contact</a>
                    </p>
                </div>

                <div class="col-md-3 col-lg-2 col-xl-2 mx-auto mb-4">
                    <h6 class="text-uppercase fw-bold mb-4">
                        Social
                    </h6>
                    <p>
                        <a href="https://www.instagram.com/noumanhoonmain/" class="text-reset" target="_blank">Instagram</a>
                    </p>
                    <p>
                        <a href="https://www.facebook.com/nouman6093" class="text-reset" target="_blank">Facebook</a>
                    </p>
                    <p>
                        <a href="https://github.com/nouman6093" class="text-reset" target="_blank">Github</a>
                    </p>
                    <p>
                        <a href="https://www.youtube.com/@nouman6093" class="text-reset" target="_blank">YouTube</a>
                    </p>
                </div>

                <div class="col-md-4 col-lg-3 col-xl-3 mx-auto mb-md-0 mb-4">
                    <h6 class="text-uppercase fw-bold mb-4">Contact</h6>
                    <p><i class="fas fa-home me-3"></i>Islamabad, Pakistan</p>
                    <p>
                        <i class="fas fa-envelope me-3"></i>
                        taskify@gmail.com
                    </p>
                    <p><i class="fas fa-phone me-3"></i> + 01 234 567 88</p>
                    <p><i class="fas fa-print me-3"></i> + 01 234 567 89</p>
                </div>
            </div>
        </div>
    </section>

    <div class="text-center p-4" style="background-color: rgba(0, 0, 0, 0.05);">
        2024 Copyright
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
