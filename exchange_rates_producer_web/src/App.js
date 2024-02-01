import './App.css';
import {useState} from "react";
import baseURL from "./components/BaseURL";

function App() {

    const [currency, setCurrency] = useState("");
    const [firstExchangeRate, setFirstExchangeRate] = useState(0);
    const [secondExchangeRate, setSecondExchangeRate] = useState(0);


    function send() {
        const requestBody = {
            currency: currency,
            rates: {
                BGR: firstExchangeRate,
                EUR: secondExchangeRate
            }
        }
        fetch(`${baseURL}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(requestBody)
        })
            .then((response) => response.json())
            .then(data => {
                console.log(data.custom);
            })
    }


    return (
        <div className="App">
            <main className="exchange-rates">
                <label htmlFor="currency">Currency</label>
                <input
                    type="text"
                    id="currency"
                    name="currency"
                    placeholder="currency"
                    value={currency}
                    onChange={(e) => setCurrency(e.target.value)}
                />
                <section className="exchange-rates-bg">
                    <label>BGR</label>
                    <input
                        type="number"
                        id="firstExchangeRate"
                        name="firstExchangeRate"
                        value={firstExchangeRate}
                        onChange={(e) => setFirstExchangeRate(e.target.valueAsNumber)}
                    />
                    <label>EUR</label>
                    <input
                        type="number"
                        id="firstExchangeRate"
                        name="firstExchangeRate"
                        value={secondExchangeRate}
                        onChange={(e) => setSecondExchangeRate(e.target.valueAsNumber)}
                    />
                </section>
                <button
                    id="submit"
                    type="button"
                    onClick={() => send()}
                >
                    send
                </button>
            </main>
        </div>
    );
}

export default App;
