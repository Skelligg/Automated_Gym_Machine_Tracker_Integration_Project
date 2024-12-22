const months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
const daysInMonth = [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]; // 2024 is a leap year
let currentMonth = 0;

const calendarBody = document.querySelector("#calendar tbody");
const currentMonthDisplay = document.getElementById("currentMonth");


// console.log(markedDates);


// Convert markedDates array to Set for more efficient lookup
const markedDatesSet = new Set(markedDates);

function renderCalendar(monthIndex) {
    currentMonthDisplay.textContent = months[monthIndex];
    calendarBody.innerHTML = "";

    const firstDay = new Date(2024, monthIndex, 1).getDay();
    let day = 1;

    // Ensure we don't exceed the number of days in the current month
    const totalDaysInMonth = daysInMonth[monthIndex];

    for (let i = 0; i < 6; i++) { // Maximum 6 rows
        const row = document.createElement("tr");

        for (let j = 0; j < 7; j++) { // 7 days in a week
            const cell = document.createElement("td");

            if ((i === 0 && j < firstDay) || day > totalDaysInMonth) {
                cell.textContent = ""; // empty cells before and after the month
            } else {
                const dateString = `${2024}-${String(monthIndex + 1).padStart(2, '0')}-${String(day).padStart(2, '0')}`;
                // console.log(dateString); // debugging
                cell.textContent = day;
                cell.setAttribute("data-date", dateString);

                // Check if this date is in the markedDatesSet and highlight it
                if (markedDatesSet.has(dateString)) {
                    console.log("Highlighting date:", dateString);  // Add this line to verify
                    cell.classList.add('bg-primary');
                    cell.classList.add('text-white');
                }


                // Increment day only if it's within the current month
                if (day <= totalDaysInMonth) {
                    day++;
                }
            }
            row.appendChild(cell);
        }

        calendarBody.appendChild(row);
    }
}



document.getElementById("prevMonth").addEventListener("click", () => {
    currentMonth = (currentMonth - 1 + 12) % 12;
    renderCalendar(currentMonth);
});

document.getElementById("nextMonth").addEventListener("click", () => {
    currentMonth = (currentMonth + 1) % 12;
    renderCalendar(currentMonth);
});

// Initialize calendar
renderCalendar(currentMonth);