import {useContext} from "react";
import {SahateContext} from "./SahateProvider.jsx";

export const useSahateContext = () => {
    const context = useContext(SahateContext);
    if (!context) {
        throw new Error('useSahateContext must be used within SahateProvider');
    }
    return context;
}