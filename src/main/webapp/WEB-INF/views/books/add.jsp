<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Book — LibraryMS</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<nav>
    <a class="nav-brand" href="${pageContext.request.contextPath}/">📚 LibraryMS</a>
    <ul class="nav-links">
        <li><a href="${pageContext.request.contextPath}/">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/authors">Authors</a></li>
        <li><a href="${pageContext.request.contextPath}/books" class="active">Books</a></li>
    </ul>
</nav>

<main>
    <div class="page-header">
        <h1 class="page-title">Add Book</h1>
    </div>

    <div class="card form-card">
        <form action="${pageContext.request.contextPath}/books/add" method="post">
            <div class="form-body">
                <div class="form-group">
                    <label for="title">Title</label>
                    <input type="text" id="title" name="title" placeholder="e.g. Nineteen Eighty-Four" required>
                </div>
                <div class="form-group">
                    <label for="genre">Genre</label>
                    <input type="text" id="genre" name="genre" placeholder="e.g. Dystopian Fiction" required>
                </div>
                <div class="form-group">
                    <label for="publishedYear">Published Year</label>
                    <input type="number" id="publishedYear" name="publishedYear" placeholder="e.g. 1949" min="1000" max="2100" required>
                </div>
                <div class="form-group">
                    <label for="authorId">Author</label>
                    <select id="authorId" name="authorId" required>
                        <option value="" disabled selected>Select an author</option>
                        <c:forEach var="author" items="${authors}">
                            <option value="${author.id}">${author.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-actions">
                <button type="submit" class="btn btn-primary">Save Book</button>
                <a href="${pageContext.request.contextPath}/books" class="btn btn-secondary">Cancel</a>
            </div>
        </form>
    </div>
</main>

</body>
</html>
