
  export default function CategoryGrid() {
    const categories = [
      { name: "Fashion", image: "https://www.onlinelogomaker.com/blog/wp-content/uploads/2017/09/fashion-logo-design.jpg" },
      { name: "Elektronik", image: "https://thumbs.dreamstime.com/b/electronic-technology-vector-logo-electronic-internet-technology-flat-vector-business-logo-template-corporate-identity-122435208.jpg" },
      { name: "Rumah Tangga", image: "https://via.placeholder.com/150x100?text=Rumah+Tangga" },
      { name: "Kecantikan", image: "https://via.placeholder.com/150x100?text=Kecantikan" },
    ];
    return (
      <div className="py-4">
        <h2 className="text-xl font-bold mb-4 text-green-800">Kategori Populer</h2>
        <div className="grid grid-cols-2 sm:grid-cols-4 gap-4">
          {categories.map((cat, idx) => (
            <div key={idx} className="bg-white p-3 rounded shadow text-center">
              <img src={cat.image} alt={cat.name} className="mx-auto mb-2 w-full h-20 object-cover" />
              <p className="text-green-700 font-medium">{cat.name}</p>
            </div>
          ))}
        </div>
      </div>
    );
  }