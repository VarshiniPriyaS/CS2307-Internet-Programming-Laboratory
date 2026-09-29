function confirmDelete() {
    return confirm("Are you sure you want to delete this product?");
}

function validateForm() {

    let password =
        document.getElementById("password");

    if (password &&
        password.value.length < 4) {

        alert("Password must contain at least 4 characters");

        return false;
    }

    return true;
}