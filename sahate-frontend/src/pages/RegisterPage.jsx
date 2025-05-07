import InputField from "../components/InputField.jsx";

const RegisterPage = () => {

    function handleOnChange(e, customValidity) {
        e.currentTarget.setCustomValidity(customValidity);
    }

    return (
        <>
            <h1>Register Page</h1>
            <form method={"post"} className={"w-1/3 mx-auto p-4 flex items-center flex-col shadow-sm rounded-lg"}>
                <InputField name={"name"} type={"text"} labelText={"Name"} />
                <InputField name={"birthDate"} type={"date"} labelText={"Birth Date"} />
                <InputField name={"address"} type={"text"} labelText={"Address"} />
                <InputField name={"phone"} type={"text"} labelText={"Phone No."} placeholder={"085xxxxx"} pattern={"^\\d{10,12}$"} onchange={(e)=>handleOnChange(e, "Nomor harus 10–12 digit angka.")} />
                <InputField name={"email"} type={"email"} labelText={"E-mail"} />
                <InputField name={"username"} type={"text"} labelText={"Username"} />
                <InputField name={"password"} type={"password"} labelText={"Password"} pattern={"^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z\\d]).{6,}$"} onchange={(e)=>handleOnChange(e, "Nomor harus 10–12 digit angka.")} title="Password minimal 6 karakter, harus ada huruf besar, huruf kecil, angka, dan simbol." />
                <button type="submit">Sign Up</button>
            </form>
        </>
    )
}

export default RegisterPage;