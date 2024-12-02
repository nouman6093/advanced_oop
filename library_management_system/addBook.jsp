<!DOCTYPE html>
<html lang="en">
<head>
    <title>Add Book</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="form-container">
    <h1>Add New Book</h1>
    <form action="AdminServlet" method="post">
        <input type="hidden" name="action" value="add">
        <label>Title:</label>
        <input type="text" name="title" required>
        <label>Author:</label>
        <input type="text" name="author" required>
        <label>Genre:</label>
        <input type="text" name="genre">
        <button type="submit">Add Book</button>
    </form>
</div>
</body>
</html>
