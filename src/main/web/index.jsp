<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Movie Recommendation System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4">🎬 Movie Recommendation System</h1>

    <div class="card mb-3">
        <div class="card-body">
            <h5 class="card-title">Users</h5>
            <p class="card-text">View all users in the system.</p>
            <a href="/api/users" class="btn btn-primary" target="_blank">View Users (JSON)</a>
        </div>
    </div>

    <div class="card mb-3">
        <div class="card-body">
            <h5 class="card-title">Movies</h5>
            <p class="card-text">View all movies in the system.</p>
            <a href="/api/movies" class="btn btn-primary" target="_blank">View Movies (JSON)</a>
        </div>
    </div>
	<div class="card mb-3">
    <div class="card-body">
        <h5 class="card-title">Ratings</h5>
        <p class="card-text">Add or view ratings via API.</p>
        <p class="card-text">Example POST via browser (replace IDs):</p>
        <pre>/API/ratings?userId=1&movieId=2&rating=FIVE</pre>
    </div>
</div>

<div class="card mb-3">
    <div class="card-body">
        <h5 class="card-title">Recommendations</h5>
        <p class="card-text">Get recommended movie for a user by ID.</p>
        <p class="card-text">Example: <code>/API/recommend/1</code></p>
    </div>
</div>
	
    
    </div>

    <footer class="mt-5 text-center">
        <p>© 2025 Movie Recommendation System</p>
    </footer>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
