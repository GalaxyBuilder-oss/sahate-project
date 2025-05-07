import { useState, useEffect } from "react";

export default function Cart() {
  const [cart, setCart] = useState([]);

  useEffect(() => {
    const storedCart = JSON.parse(localStorage.getItem("cart")) || [];
    setCart(storedCart);
  }, []);

  function handleRemove(index) {
    const newCart = [...cart];
    newCart.splice(index, 1);
    setCart(newCart);
    localStorage.setItem("cart", JSON.stringify(newCart));
  }

  if (cart.length === 0) {
    return <div className="p-4 text-center text-gray-600">Keranjang belanja masih kosong.</div>;
  }

  return (
    <div className="p-6 max-w-3xl mx-auto">
      <h2 className="text-2xl font-bold mb-4 text-green-700">Keranjang Belanja</h2>
      {cart.map((item, idx) => (
        <div key={idx} className="flex items-center gap-4 border-b py-4">
          <img src={item.image} alt={item.name} className="w-20 h-20 object-cover rounded" />
          <div className="flex-1">
            <h3 className="text-lg font-semibold text-green-800">{item.name}</h3>
            <p className="text-gray-700">Rp{item.price}</p>
          </div>
          <button
            onClick={() => handleRemove(idx)}
            className="text-red-500 hover:underline"
          >
            Hapus
          </button>
        </div>
      ))}
    </div>
  );
}
