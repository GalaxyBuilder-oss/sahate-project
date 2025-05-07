import { Link } from "react-router-dom";

export default function Navbar() {
  return (
    <nav className="bg-green-700 text-white p-4 flex justify-between shadow-md">
      <Link to="/" className="font-bold text-xl">TokoHijau</Link>
      <div>
        <Link to="/cart" className="mx-2 hover:underline">Cart</Link>
        <Link to="/login" className="mx-2 hover:underline">Login</Link>
      </div>
    </nav>
  );
}