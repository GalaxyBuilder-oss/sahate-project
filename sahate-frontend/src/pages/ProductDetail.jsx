import { useParams } from "react-router-dom";



const dummyProducts = [
  { id: 1, name: "Kaos Polos", price: 50000, image: "https://via.placeholder.com/300x200?text=Kaos+Polos" },
  { id: 2, name: "Jaket Hoodie", price: 150000, image: "https://via.placeholder.com/300x200?text=Jaket+Hoodie" },
];

export default function ProductDetail() {
  const { id } = useParams();
  const product = dummyProducts.find(p => p.id === parseInt(id));
  if (!product) return <div className="p-4">Produk tidak ditemukan.</div>;

  function handleAddToCart() {
    const cart = JSON.parse(localStorage.getItem("cart")) || [];
    if (!cart.find(item => item.id === product.id)) {
      cart.push(product);
      localStorage.setItem("cart", JSON.stringify(cart));
      alert("Produk ditambahkan ke keranjang");
    } else {
      alert("Produk sudah ada di keranjang");
    }
  }

  return (
    <div className="p-6 max-w-2xl mx-auto bg-white rounded shadow">
      <h2 className="text-2xl font-bold mb-2 text-green-800">{product.name}</h2>
      <img src={product.image} alt={product.name} className="mb-4 w-full object-cover rounded" />
      <p className="text-gray-800 mb-2">Harga: Rp{product.price}</p>
      <button
        onClick={handleAddToCart}
        className="mt-4 px-4 py-2 bg-green-600 hover:bg-green-700 text-white rounded"
      >
        Tambah ke Keranjang
      </button>
    </div>
  );
}
