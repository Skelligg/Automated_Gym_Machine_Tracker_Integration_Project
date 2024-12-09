    let chart;


function showTable(tableId,chartType, clickedLabel){
    //hiding all tables
    document.querySelectorAll('div[id$="Table"]').forEach(table=>{table.style.display = 'none';});

    //show the selected table
    document.getElementById(tableId).style.display ='block'
    //remove active class
    document.querySelectorAll('.btn').forEach(button => {button.classList.remove('active');});

    //adding the active class to the clicked one
    clickedLabel.classList.add('active');
    fetchChartData(chartType)

    //fetching the chart and checking what type should it be
    function fetchChartData(chartType) {


        const gymGoerId = 1
        const machineId = 52

        fetch(`/getChartData/${chartType}?gymGoerId=${gymGoerId}&machineId=${machineId}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('failed to load')
                }
                        return response.json();
            }).then(data => {
                console.log(data)
                const labels = data.map(entry => entry.date);
                let chartData;

                // const chartData = data.map(entry => entry.value);
            if (chartType === 'weights') {
                chartData = data.map(entry => entry.weightCount);
            } else if (chartType === 'volume'){
                chartData = data.map(entry => entry.volumeD)
            } else if (chartType === 'repetitions'){
                chartData = data.map(entry => entry.repCount || 0);
            }


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
                        fill: false,
                        tension: 0.4
                    }]
                },
                options: {
                    responsive: true,
                    scales: {
                        x: {
                            title: {
                                display: true,
                                //the name of the x-date
                                text: 'Date'
                            }
                        },
                        y: {
                            title: {
                                display: true,
                                // the name of the Y-axis
                                text: getYAxisLabel(chartType)
                            }
                        }
                    }
                }
            });
        }).catch(error => {
            console.error('There was an error to find the data:', error);
            alert('There was an error fetching the data');
        });
    }

    function getChartLabel(chartType) {
        switch (chartType) {
            case 'weights': return 'Weight Lifted';
            case 'volume':return 'Volume Lifted';
            case 'reps':return 'Total Reps';
            default: return 'Data';
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