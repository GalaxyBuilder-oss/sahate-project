import {useSahateContext} from "../contexts/useSahateContext.js";

const HomePage = () => {
    const {dummy} = useSahateContext();
    return (
        <>
            {dummy}
            <h1 className="text-3xl font-bold underline">
                Check
            </h1>
        </>
    )
}

export default HomePage;