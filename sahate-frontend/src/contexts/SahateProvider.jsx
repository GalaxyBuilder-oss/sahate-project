import {createContext} from "react";

export const SahateContext = createContext({});

const SahateProvider = ({children}) => {

    const dummy = [1,2,3]
    return (
        <SahateContext.Provider value={{dummy}}>
            {children}
        </SahateContext.Provider>
    )
}

export default SahateProvider