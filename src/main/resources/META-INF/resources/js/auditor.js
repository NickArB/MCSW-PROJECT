var token;
var userInfo;

$(document).ready(function() {
    token = sessionStorage.getItem('jwtToken');
    const parts = token.split('.');
    userInfo = JSON.parse(atob(parts[1]));
    showAuditor();
});

function showAuditor() {
    if (userInfo === null) {
        window.location.href = 'login.xhtml';
    } else if (userInfo.Role !== 'AUDITOR') {
       window.location.href = 'userUnauthorized.xhtml';
    }
}
