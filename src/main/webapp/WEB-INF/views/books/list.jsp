<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Books — LibraryMS</title>
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
    <c:if test="${not empty success}">
        <div class="alert alert-success">✓ ${success}</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert alert-error">✕ ${error}</div>
    </c:if>

    <div class="page-header">
        <h1 class="page-title">Books</h1>
        <a href="${pageContext.request.contextPath}/books/add" class="btn btn-primary">+ Add Book</a>
    </div>

    <div class="card">
        <div class="table-wrapper">
            <table>
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Title</th>
                        <th>Genre</th>
                        <th>Year</th>
                        <th>Author</th>
                        <th>Nationality</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="book" items="${books}">
                        <tr>
                            <td>${book.bookId}</td>
                            <td>${book.title}</td>
                            <td>${book.genre}</td>
                            <td>${book.publishedYear}</td>
                            <td>${book.authorName}</td>
                            <td style="color: #64748b; font-size: 0.875rem;">${book.authorNationality}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/books/edit/${book.bookId}" class="btn btn-secondary btn-sm">Edit</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty books}">
                        <tr>
                            <td colspan="7" style="text-align:center; color: #64748b; padding: 2rem;">No books found.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</main>

</body>
</html>
