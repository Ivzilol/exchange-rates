import './App.css';
import {useState} from "react";
import baseURL from "./components/BaseURL";

function App() {

    const [currency, setCurrency] = useState("");
    const [firstCountry, setFirstCountry] = useState("");
    const [firstExchangeRate, setFirstExchangeRate] = useState(0);
    const [secondCountry, setSecondCountry] = useState("");
    const [secondExchangeRate, setSecondExchangeRate] = useState(0);


    function send() {
        let first = firstCountry;
        let second = secondCountry;
        const requestBody = {
            currency: currency,
            rates: {
                first: firstExchangeRate,
                second: secondExchangeRate
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
                    <label>Country 1</label>
                    <input
                        type="text"
                        id="country"
                        name="country"
                        placeholder="Country"
                        value={firstCountry}
                        onChange={(e) => setFirstCountry(e.target.value)}
                    />
                    <input
                        type="number"
                        id="firstExchangeRate"
                        name="firstExchangeRate"
                        value={firstExchangeRate}
                        onChange={(e) => setFirstExchangeRate(e.target.valueAsNumber)}
                    />
                    <label>Country 2</label>
                    <input
                        type="text"
                        id="country"
                        name="country"
                        placeholder="Country"
                        value={secondCountry}
                        onChange={(e) => setSecondCountry(e.target.value)}
                    />
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
