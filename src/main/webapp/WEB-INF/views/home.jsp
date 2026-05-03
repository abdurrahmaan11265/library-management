<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Library Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<nav>
    <a class="nav-brand" href="${pageContext.request.contextPath}/">📚 LibraryMS</a>
    <ul class="nav-links">
        <li><a href="${pageContext.request.contextPath}/" class="active">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/authors">Authors</a></li>
        <li><a href="${pageContext.request.contextPath}/books">Books</a></li>
    </ul>
</nav>

<main>
    <div class="welcome-section">
        <h1 class="welcome-title">Welcome to LibraryMS</h1>
        <p class="welcome-subtitle">Manage your authors and books from one place.</p>
    </div>

    <div class="stats-grid">
        <a class="stat-card" href="${pageContext.request.contextPath}/authors">
            <div class="stat-number">${authorCount}</div>
            <div class="stat-label">Authors</div>
        </a>
        <a class="stat-card" href="${pageContext.request.contextPath}/books">
            <div class="stat-number">${bookCount}</div>
            <div class="stat-label">Books</div>
        </a>
    </div>

    <div class="quick-links">
        <h2>Quick Actions</h2>
        <div class="link-grid">
            <a href="${pageContext.request.contextPath}/authors/add" class="btn btn-primary">+ Add Author</a>
            <a href="${pageContext.request.contextPath}/books/add" class="btn btn-primary">+ Add Book</a>
            <a href="${pageContext.request.contextPath}/authors" class="btn btn-secondary">View All Authors</a>
            <a href="${pageContext.request.contextPath}/books" class="btn btn-secondary">View All Books</a>
        </div>
    </div>
</main>

</body>
</html>
