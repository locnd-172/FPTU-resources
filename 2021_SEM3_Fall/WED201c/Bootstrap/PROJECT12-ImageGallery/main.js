const images = document.querySelectorAll(".container .image img");
const gallery = document.querySelector(".gallery");
const galleryImg = document.querySelector(".gallery-inner img");
const prevBtn = document.querySelector(".control.prev");
const nextBtn = document.querySelector(".control.next");

const closeBtn = document.querySelector(".gallery .close");

let curId = 0;

images.forEach((image, id) => {
    image.addEventListener("click", () => {
        curId = id;
        showGallery();
    });
});

function showGallery() {
    curId == images.length - 1
        ? nextBtn.classList.add("hide")
        : nextBtn.classList.remove("hide");

    curId == 0
        ? prevBtn.classList.add("hide")
        : prevBtn.classList.remove("hide");

    gallery.classList.add("show");
    galleryImg.src = images[curId].src;

}

closeBtn.addEventListener("click", () => {
    gallery.classList.remove("show");
});

function moveToNextImg() {
    curId != images.length - 1 ? curId++ : undefined;
    showGallery();
}

function moveToPrevImg() {
    curId != 0 ? curId-- : undefined;
    showGallery();
}

nextBtn.addEventListener("click", () => {
    moveToNextImg();
});

prevBtn.addEventListener("click", () => {
    moveToPrevImg();
});

document.addEventListener("keydown", (event) => {
    if (event.keyCode == 27) gallery.classList.remove("show");
    else if (event.keyCode == 37) moveToPrevImg();
    else if (event.keyCode == 39) moveToNextImg();

})

