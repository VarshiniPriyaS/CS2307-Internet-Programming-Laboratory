let xmlData;

function loadProducts() {

    let xhr = new XMLHttpRequest();

    xhr.open("GET", "products.xml", true);

    xhr.onload = function () {

        if (xhr.status == 200) {

            xmlData = xhr.responseXML;

            displayProducts(xmlData);
        }
        else {
            document.getElementById("products").innerHTML =
                "<p>Unable to load products.</p>";
        }
    };

    xhr.send();
}


function displayProducts(xml) {

    let products = xml.getElementsByTagName("product");

    let output = "";

    for (let i = 0; i < products.length; i++) {

        let id =
            products[i].getElementsByTagName("id")[0].textContent;

        let name =
            products[i].getElementsByTagName("name")[0].textContent;

        let category =
            products[i].getElementsByTagName("category")[0].textContent;

        let price =
            products[i].getElementsByTagName("price")[0].textContent;

        let stock =
            products[i].getElementsByTagName("stock")[0].textContent;


        output += `
            <div class="product">

                <h3>${name}</h3>

                <p><b>Product ID:</b> ${id}</p>

                <p><b>Category:</b> ${category}</p>

                <p><b>Price:</b> ₹${price}</p>

                <p><b>Available Stock:</b> ${stock}</p>

                <button onclick="addToCart('${name}', '${price}')">
                    Add to Cart
                </button>

            </div>
        `;
    }

    document.getElementById("products").innerHTML = output;
}


function searchProduct() {

    if (!xmlData) {
        return;
    }

    let searchValue =
        document.getElementById("search").value.toLowerCase();

    let products =
        xmlData.getElementsByTagName("product");

    let output = "";

    for (let i = 0; i < products.length; i++) {

        let name =
            products[i].getElementsByTagName("name")[0].textContent;

        let category =
            products[i].getElementsByTagName("category")[0].textContent;

        let price =
            products[i].getElementsByTagName("price")[0].textContent;

        let stock =
            products[i].getElementsByTagName("stock")[0].textContent;

        if (
            name.toLowerCase().includes(searchValue) ||
            category.toLowerCase().includes(searchValue)
        ) {

            output += `
                <div class="product">

                    <h3>${name}</h3>

                    <p><b>Category:</b> ${category}</p>

                    <p><b>Price:</b> ₹${price}</p>

                    <p><b>Stock:</b> ${stock}</p>

                    <button onclick="addToCart('${name}', '${price}')">
                        Add to Cart
                    </button>

                </div>
            `;
        }
    }

    if (output == "") {
        output = "<p>No products found.</p>";
    }

    document.getElementById("products").innerHTML = output;
}


function addToCart(name, price) {

    alert(
        name + " added to cart!\nPrice: ₹" + price
    );
}