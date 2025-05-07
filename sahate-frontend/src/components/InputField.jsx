const InputField = ({type, name, labelText, onchange, ...rest}) => {
    return (
        <>
            <div className={"my-4 flex justify-between"}>
                <label form={name} className={"w-30 uppercase"}>{labelText}</label>
                <input type={type} id={name} name={name} onChange={onchange} className={"w-70 border-b border-b-gray-300 p-2 text-sm rounded-lg font-light"} {...rest} />
            </div>
        </>
    )
}

export default InputField;