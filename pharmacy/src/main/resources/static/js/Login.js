const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

signUpButton.addEventListener('click', () => {
    container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
    container.classList.remove("right-panel-active");
});

document.getElementById("myBtn").addEventListener("click", signup);

function signup() {
    const firstName = document.getElementById('firstName').value;
    const lastName = document.getElementById('lastName').value;
    const email = document.getElementById('emailAddress').value;
    const password = document.getElementById('pass').value;
    const address = document.getElementById('address').value;
    const city = document.getElementById('city').value;
    const country = document.getElementById('country').value;
    const phoneNumber = document.getElementById('phoneNumber').value;

    const postdata = {
        firstName: firstName,
        lastName: lastName,
        email: email,
        password: password,
        address: address,
        city: city,
        country: country,
        phoneNumber: phoneNumber
    };

    const ajaxRequest = new XMLHttpRequest();
    ajaxRequest.open('POST', 'http://localhost:8080/signup');
    ajaxRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ajaxRequest.send(JSON.stringify(postdata));
}