// File: src/components/BannerCarousel.js
import { useEffect, useState } from "react";

const banners = [
  "https://via.placeholder.com/1200x300/8BC34A/ffffff?text=Diskon+Hari+Ibu",
  "https://png.pngtree.com/png-clipart/20220823/original/pngtree-banner-spesial-gratis-ongkir-biru-kuning-belanja-online-promo-png-vektor-png-image_8447157.png",
  "https://th.bing.com/th/id/OIP.S-4Fg5DSc3_fuHUWxephsgHaHa?rs=1&pid=ImgDetMain",
];

export default function BannerCarousel() {
  const [current, setCurrent] = useState(0);

  useEffect(() => {
    const timer = setInterval(() => {
      setCurrent(prev => (prev + 1) % banners.length);
    }, 4000);
    return () => clearInterval(timer);
  }, []);

  return (
    <div className="relative overflow-hidden mb-6">
      <img
        src={banners[current]}
        alt={`Banner ${current + 1}`}
        className="w-full h-[200px] sm:h-[300px] object-cover transition-all duration-700 ease-in-out rounded"
      />
    </div>
  );
}