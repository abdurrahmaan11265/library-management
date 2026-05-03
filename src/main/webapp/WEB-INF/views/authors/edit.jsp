<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Author — LibraryMS</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<nav>
    <a class="nav-brand" href="${pageContext.request.contextPath}/">📚 LibraryMS</a>
    <ul class="nav-links">
        <li><a href="${pageContext.request.contextPath}/">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/authors" class="active">Authors</a></li>
        <li><a href="${pageContext.request.contextPath}/books">Books</a></li>
    </ul>
</nav>

<main>
    <div class="page-header">
        <h1 class="page-title">Edit Author</h1>
    </div>

    <div class="card form-card">
        <form action="${pageContext.request.contextPath}/authors/edit/${author.id}" method="post">
            <div class="form-body">
                <div class="form-group">
                    <label for="name">Full Name</label>
                    <input type="text" id="name" name="name" value="${author.name}" required>
                </div>
                <div class="form-group">
                    <label for="nationality">Nationality</label>
                    <input type="text" id="nationality" name="nationality" value="${author.nationality}" required>
                </div>
                <div class="form-group">
                    <label for="bio">Bio</label>
                    <textarea id="bio" name="bio">${author.bio}</textarea>
                </div>
            </div>
            <div class="form-actions">
                <button type="submit" class="btn btn-primary">Update Author</button>
                <a href="${pageContext.request.contextPath}/authors" class="btn btn-secondary">Cancel</a>
            </div>
        </form>
    </div>
</main>

</body>
</html>
