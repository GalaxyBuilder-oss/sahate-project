import {StrictMode} from 'react'
import {createRoot} from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import HomePage from "./pages/HomePage.jsx";
import {createBrowserRouter, RouterProvider} from "react-router-dom";

const routes = createBrowserRouter([{
    path: "/",
    element: <App />,
    children: [
        {
            path: "/",
            element: <HomePage />,

        }
    ]
}])

createRoot(document.getElementById('root')).render(
    <StrictMode>
        <RouterProvider router={routes}></RouterProvider>
    </StrictMode>,
)
