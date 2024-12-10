document.addEventListener("DOMContentLoaded", function () {
    console.log("ебаная");
    document.addEventListener("click", (e) => {
        console.log("хуйня");
        if (e.target.closest('svg')) {
            console.log("хуйня 2");
            graphClick(e)
        }
    });
});

function graphClick(e) {
    const svgX = e.offsetX;
    const svgY = e.offsetY;

    const x = (svgX - 150) / 25;
    const y = (150 - svgY) / 25;

    // Найти выбранное значение R через DOM
    const rButton = document.querySelector('div[id$="r"] input:checked');
    const r = rButton ? rButton.value : null;

    console.log("Calculated values:");
    console.log("X:", x);
    console.log("Y:", y);
    console.log("R:", r);

    if (!r) {
        console.error("Value for R is not selected!");
        return;
    }

    // Присвоение значений скрытым полям
    const hiddenX = document.querySelector('[id$="hiddenX"]');
    const hiddenY = document.querySelector('[id$="hiddenY"]');
    const submitButton = document.querySelector('[id$="submitButton"]');

    if (!hiddenX || !hiddenY || !submitButton) {
        console.error("Hidden fields or submit button not found!");
        return;
    }

    hiddenX.value = x;
    hiddenY.value = y;

    // Логирование значений скрытых полей после их записи
    console.log("Hidden field values after assignment:");
    console.log("Hidden X:", hiddenX.value);
    console.log("Hidden Y:", hiddenY.value);
    // submitButton.click();
    updateCoordinates();
    checkHit();
    some();

}