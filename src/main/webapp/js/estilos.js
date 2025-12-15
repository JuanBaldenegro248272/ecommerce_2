/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.addEventListener("DOMContentLoaded", () => {
    const tarjetasGenero = document.querySelectorAll(".genero");

    tarjetasGenero.forEach(card => {
        card.addEventListener("click", () => {
            const infoTexto = card.querySelector(".info p").innerText.trim();
            let generoParaUrl = NombreGenero(infoTexto);
            window.location.href = `formato.jsp?genero=${encodeURIComponent(generoParaUrl)}`;
        });
    });
});

function NombreGenero(textoVisible) {
    const texto = textoVisible.toUpperCase();
    if (texto.includes("ALTERNATIVE") || texto.includes("ROCK"))
        return "Rock";
    if (texto === "NEW")
        return "Nuevos";
    if (texto === "HIP HOP")
        return "Hip Hop";
    if (texto === "CHILL OUT")
        return "Chill";
    if (texto === "R&B")
        return "R&B";
    return textoVisible;
}
