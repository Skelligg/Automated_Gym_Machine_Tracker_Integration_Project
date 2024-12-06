    let chart;

function showTable(tableId,chartType, clickedLabel){
    //hiding all tables
    document.querySelectorAll('div[id$="Table"]').forEach(table=>{table.style.display = 'none';});

    //show the selected table
    document.getElementById(tableId).style.display ='block'
    //remove active class
    document.querySelectorAll('.btn').forEach(button => {button.classList.remove('active');});

    clickedLabel.classList.add('active');
    fetchChartData(chartType)

    function fetchChartData(chartType) {
        fetch(`/getChartData/${chartType}`).then(response => response.json()).then(data => {
            const labels = data.map(entry => entry.dateTime);
            const chartData = data.map(entry => entry.value);

            // removing the previous chart
            if (chart) {
                chart.destroy();
            }

            // chart based on the pressed button
            const ctx = document.getElementById('chartCanvas').getContext('2d');
            chart = new Chart(ctx, {
                type: 'line',
                data: {
                    labels: labels,
                    datasets: [{
                        label: getChartLabel(chartType),
                        data: chartData,
                        borderColor: 'rgb(0, 0, 0)',
                        backgroundColor: 'rgba(0,0,0)',
                        borderWidth: 2,
                        fill: false
                    }]
                },
                options: {
                    responsive: true,
                    scales: {
                        x: {
                            title: {
                                display: true,
                                text: 'Date'
                            }
                        },
                        y: {
                            title: {
                                display: true,
                                text: getYAxisLabel(chartType)
                            }
                        }
                    }
                }
            });
        });
    }

    function getChartLabel(chartType) {
        switch (chartType) {
            case 'weights': return 'Weight Lifted';
            case 'volume':return 'Volume Lifted';
            case 'reps':return 'Total Reps';
            default:return 'Data';
        }
    }
    function getYAxisLabel(chartType) {
        switch (chartType) {
            case 'weights':return 'Weight (kg)';
            case 'volume':return 'Volume (kg)';
            case 'reps':return 'Reps';
            default:return 'Value';
        }
    }

}