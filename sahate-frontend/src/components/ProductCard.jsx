
import { Link } from "react-router-dom";
export default function ProductCard({ product }) {
  function handleAddToCart() {
    const cart = JSON.parse(localStorage.getItem("cart") || "[]");
    cart.push(product);
    localStorage.setItem("cart", JSON.stringify(cart));
    alert(`${product.name} ditambahkan ke keranjang.`);
  }

  return (
    <div className="border rounded shadow hover:shadow-xl transition-all duration-300 p-4 bg-white flex flex-col">
      <img src={product.image} alt={product.name} className="mb-3 w-full h-40 object-cover rounded" />
      <h3 className="text-lg font-semibold text-green-700">{product.name}</h3>
      <p className="text-gray-800 font-medium mb-3">Rp{product.price}</p>
      <div className="mt-auto">
        <button
          onClick={handleAddToCart}
          className="inline-block w-full text-center bg-green-600 text-white py-2 px-4 rounded hover:bg-green-700 transition"
        >
          Tambah ke Keranjang
        </button>
      </div>
    </div>
  );
}
