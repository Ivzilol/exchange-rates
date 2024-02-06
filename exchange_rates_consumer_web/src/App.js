import './App.css';
import {useEffect, useState} from "react";

function App() {

    const [courses, setCourses] = useState(null);

    useEffect(() => {
        fetch(`http://localhost:7070/`, {
            method: "GET"
        })
            .then((response) => response.json())
            .then(data => {
                    setCourses(data)
            })
    }, [])


    return (
        <div className="App">
            <main className="exchange-rates">
                Proba
                <section className="exchange-rates-container">
                    {courses ?
                        <div className="exchange-rates-container-courses">
                            {courses.map((course) => (
                                <div>
                                    <h4>{course.currency}</h4>
                                    <p>{course.rates.EUR}</p>
                                    <p>{course.rates.BGR}</p>
                                    <></>
                                </div>
                            ))}
                        </div>
                        :
                        <></>
                    }
                </section>
            </main>
        </div>
    );
}

export default App;
