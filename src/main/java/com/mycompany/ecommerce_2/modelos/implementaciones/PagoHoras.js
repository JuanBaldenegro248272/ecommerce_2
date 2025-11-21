function totalAPagar(horasTrabajadas){
    const pagoPorHora = 120;

    let horasNormales;
    let horasExtra;

    if (horasTrabajadas > 40) {
    horasNormales = 40;
    horasExtra = horasTrabajadas - 40;
    } else {
        horasNormales = horasTrabajadas;
        horasExtra = 0;
    }
    let pagoHorasNormales = horasNormales * pagoPorHora;
    let pagoHorasExtra = horasExtra * (pagoPorHora * 1.5);

    let totalPagar = pagoHorasNormales + pagoHorasExtra;

    console.log("Horas normales: " + horasNormales);
    console.log("Horas extra: " + horasExtra);
    console.log("Pago por horas normales: $" + pagoHorasNormales);
    console.log("Pago por horas extra: $" + pagoHorasExtra);
    console.log("Total a pagar: $" + totalPagar);
}

