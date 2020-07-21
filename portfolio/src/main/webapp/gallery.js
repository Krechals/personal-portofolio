
var travelSlideshow = {
  slideIndex: 0,
  plusSlides: function(offset) {
    this.showSlides(this.slideIndex += offset);
  }, 
  currentSlide: function(newSlideIndex) {
    this.showSlides(this.slideIndex = newSlideIndex);
  },
  hideAllSlides: function(slides) {
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";  
    }
  },
  disableAllDots: function(dots) {
    for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" active", "");
    }
  },
  showSlides: function(activatedSlide) {
    var slides = document.getElementsByClassName("slides");
    var dots = document.getElementsByClassName("dot");
    if (slides == undefined || slides.length == 0
        || dots == undefined || dots.length == 0) {
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'No slideshow available!',
        footer: '<a href="passions.html">Maybe try again!</a>'
      })
      return;
    }
    // Keeps counter in the proper interval
    if (activatedSlide >= slides.length) {
      this.slideIndex = 0;
    }  
    if (activatedSlide < 0) {
      this.slideIndex = slides.length - 1;
    }

    this.hideAllSlides(slides);
    this.disableAllDots(dots);

    // Make current photo visible
    slides[this.slideIndex].style.display = "block";  
    dots[this.slideIndex].className += " active";

    // Autoplay at every 3 seconds.
    setTimeout(this.plusSlides.bind(this), 3000, 1);
  }
}

// Execute this after page loads
document.addEventListener('DOMContentLoaded', function() {
  travelSlideshow.showSlides(0);
}, false);
