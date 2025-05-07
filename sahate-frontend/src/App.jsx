import NavigationComponent from "./components/NavigationComponent.jsx";
import FooterComponent from "./components/FooterComponent.jsx";
import {Outlet} from "react-router-dom";
import SahateProvider from "./contexts/SahateProvider.jsx";

function App() {
    return (
        <>
            <SahateProvider>
                <NavigationComponent/>
                <main className={"w-full"}>
                    <Outlet/>
                </main>
                <FooterComponent/>
            </SahateProvider>
        </>
    )
}

export default App
