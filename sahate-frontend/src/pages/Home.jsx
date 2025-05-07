import ProductCard from "../components/ProductCard";
import BannerCarousel from "../components/BannerCarousel";
import CategoryGrid from "../components/CategoryGrid";

const dummyProducts = [
  { id: 1, name: "Kaos Polos", price: 50000, image: "https://via.placeholder.com/300x200?text=Kaos+Polos" },
  { id: 2, name: "Jaket Hoodie", price: 150000, image: "https://via.placeholder.com/300x200?text=Jaket+Hoodie" },
  { id: 3, name: "Sneakers", price: 250000, image: "https://via.placeholder.com/300x200?text=Sneakers" },
  { id: 4, name: "Celana Jeans", price: 180000, image: "https://via.placeholder.com/300x200?text=Celana+Jeans" },
];

export default function Home() {
  return (
    <div className="bg-gray-50 min-h-screen">
      <BannerCarousel />
      <div className="px-6 max-w-screen-xl mx-auto">
        <CategoryGrid />
        <header className="bg-green-100 text-green-900 p-6 text-center rounded mt-6">
          <h1 className="text-3xl font-bold">Promo Spesial Hari Ini</h1>
          <p className="text-sm">Belanja hemat dengan diskon hingga 50%</p>
        </header>
        <div className="py-6 grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
          {dummyProducts.map(product => (
            <ProductCard key={product.id} product={product} />
          ))}
        </div>
      </div>
    </div>
  );
}
