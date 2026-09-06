const stepInfo = document.getElementById("stepInfo");
const navLeftGroup = document.getElementById("navLeft");
const navRightGroup = document.getElementById("navRight");

const nameInput = document.getElementById("name");
const genderInput = document.getElementById("gender");
const emailInput = document.getElementById("email");
const birthdateInput = document.getElementById("birthdate");
const villInput = document.getElementById("vill");
const distInput = document.getElementById("dist");
const stateInput = document.getElementById("state");
const countryInput = document.getElementById("country");
const documentInput = document.getElementById("document");
const departmentInput = document.getElementById("department");
const userNameInput = document.getElementById("user");
const passInput = document.getElementById("pass");
const contactInput = document.getElementById("contactNo");

const highInput = document.getElementById("high-m");
const interInput = document.getElementById("inter-m");
const branchInput = document.getElementById("branch");
const skillsInput = document.getElementById("skills");
const termsCheckbox = document.getElementById("terms");
const termCheckbox = document.getElementById("term");

const nameVal = document.getElementById("name-val");
const genVal = document.getElementById("gen-val");
const emailVal = document.getElementById("email-val");
const bdVal = document.getElementById("bd-val");
const cvVal = document.getElementById("cv-val");
const deptVal = document.getElementById("dept-val");
const usVal = document.getElementById("us-val");
const paVal = document.getElementById("pa-val");
const contVal = document.getElementById("cont-val");
const hiVal = document.getElementById("hi-val");
const brVal = document.getElementById("br-val");
const intVal = document.getElementById("int-val");
const skillsVal = document.getElementById("skills-val");
const vilVal = document.getElementById("vil-val");
const diVal = document.getElementById("di-val");
const stVal = document.getElementById("st-val");
const coVal = document.getElementById("co-val");












const form = document.getElementById("myForm");
const formStepsID = ["one", "two", "three-1", "four"];
let currentFormStep = 0;

const editsButtons = {
    "name-edit": 0,
    "gen-edit": 0,
    "email-edit": 0,
    "bd-edit": 0,
    "cv-edit": 1,
    "vil-edit": 1,
    "di-edit": 1,
    "st-edit": 1,
    "co-edit": 1,
    "dept-edit": 1,
    "us-edit": 2,
    "pa-edit": 2,
    "cont-edit": 2,
    "hi-edit": 2,
    "int-edit": 2,
    "br-edit": 2,
    "skills-edit": 2,
};

const updateSummaryValues = () => {
    nameVal.textContent = nameInput.value;
    genVal.textContent = genderInput.value;
    emailVal.textContent = emailInput.value;
    bdVal.textContent = birthdateInput.value;
    vilVal.textContent = villInput.value;
    diVal.textContent = distInput.value;
    stVal.textContent = stateInput.value;
    coVal.textContent = countryInput.value;
    const fileName = documentInput.files[0]?.name;
    if (fileName) {
        const extension = fileName.split(".").pop();
        const baseName = fileName.split(".")[0];
        const truncatedName =
            baseName.length > 10 ? baseName.substring(0, 10) + "..." : baseName;
        cvVal.textContent = `${truncatedName}.${extension}`;
    } else {
        cvVal.textContent = "No file selected";
    }
    deptVal.textContent = departmentInput.value;
    usVal.textContent = userNameInput.value;
    paVal.textContent = passInput.value;
    contVal.textContent = contactInput.value;
    hiVal.textContent = highInput.value;
    intVal.textContent = interInput.value;
    brVal.textContent = branchInput.value;
    skillsVal.textContent = skillsInput.value || "No skills submitted";
};

const updateStepVisibility = () => {
    formStepsID.forEach((step) => {
        document.getElementById(step).style.display = "none";
    });

    document.getElementById(formStepsID[currentFormStep]).style.display = "block";

    stepInfo.textContent = `Step ${currentFormStep + 1} of ${formStepsID.length}`;

    if (currentFormStep === 3) {
        updateSummaryValues();
    }

    navLeftGroup.style.display = currentFormStep === 0 ? "none" : "block";
    navRightGroup.style.display =
        currentFormStep === formStepsID.length - 1 ? "none" : "block";

    const currentStep = document.getElementById(formStepsID[currentFormStep]);
    const firstInput = currentStep.querySelector("input, select ,textarea");
    if (firstInput) {
        firstInput.focus();
    }
};

const showError = (input, message) => {
    const formControl = input.parentElement;
    const errorSpan = formControl.querySelector(".error-message");
    input.classList.add("error");
    input.setAttribute("aria-invalid", "true");
    input.setAttribute("aria-describedby", errorSpan.id);
    errorSpan.textContent = message;
};

const clearError = (input) => {
    const formControl = input.parentElement;
    const errorSpan = formControl.querySelector(".error-message");
    input.classList.remove("error");
    input.removeAttribute("aria-invalid");
    input.removeAttribute("aria-describedby");
    errorSpan.textContent = "";
};

const validateStep = (currentStep) => {
    let isValid = true;

    if (currentStep === 0) {
        if (nameInput.value.trim() === "") {
            showError(nameInput, "Name is required");
            isValid = false;
        }
        if (genderInput.value === "") {
            showError(genderInput, " Gender section is required");
            isValid = false;
        }
        if (emailInput.value.trim() === "" || !emailInput.validity.valid) {
            showError(emailInput, "Enter valid email is required");
            isValid = false;
        }
        if (birthdateInput.value === "") {
            showError(birthdateInput, "Date of birth is required");
            isValid = false;
        }
        if (!termCheckbox.checked) {
            showError(termCheckbox, "Terms and conditions must be accepted");
            isValid = false;
        }
    } else if (currentStep === 1) {
        if (villInput.value.trim() === "") {
            showError(villInput, "vill  is required");
            isValid = false;
        }
        if (distInput.value.trim() === "") {
            showError(distInput, "district  is required");
            isValid = false;
        }
        if (stateInput.value === "") {
            showError(stateInput, "state is required");
            isValid = false;
        }
        if (countryInput.value === "") {
            showError(countryInput, "country  is required");
            isValid = false;
        }
        if (!documentInput.files[0]) {
            showError(documentInput, "Images is required");
            isValid = false;
        }
        if (departmentInput.value === "") {
            showError(departmentInput, "Department selection is required");
            isValid = false;
        }
    } else if (currentStep === 2) {
        if (userNameInput.value.trim() === "") {
            showError(userNameInput, "username  is required");
            isValid = false;
        }
        if (passInput.value.trim() === "") {
            showError(passInput, "password  is required");
            isValid = false;
        }
        if (contactInput.value.trim() === "") {
            showError(contactInput, "contact no is required");
            isValid = iti.isValidNumber() == true ? true : false;
        }
        if (highInput.value.trim() === "") {
            showError(highInput, "10th marks is required");
            isValid = false;
        }
        if (interInput.value.trim() === "") {
            showError(interInput, "12th marks  is required");
            isValid = false;
        }
        if (branchInput.value === "") {
            showError(branchInput, "branch  is required");
            isValid = false;
        }
        if (!termsCheckbox.checked) {
            showError(termsCheckbox, "Terms and conditions must be accepted");
            isValid = false;
        }

    }

    return isValid;
};

const realtimeValidation = () => {
    nameInput.addEventListener("input", () => {
        if (nameInput.value.trim() !== "") clearError(nameInput);
    });
    genderInput.addEventListener("change", () => {
        if (genderInput.value !== "") clearError(genderInput);
    });

    emailInput.addEventListener("input", () => {
        if (emailInput.value.trim() !== "") clearError(emailInput);
    });

    birthdateInput.addEventListener("change", () => {
        if (birthdateInput.value !== "") clearError(birthdateInput);
    });
    termCheckbox.addEventListener("change", () => {
        if (termCheckbox.checked) clearError(termCheckbox);
    });
    villInput.addEventListener("input", () => {
        if (villInput.value.trim() !== "") clearError(villInput);
    });
    distInput.addEventListener("input", () => {
        if (distInput.value.trim() !== "") clearError(distInput);
    });
    stateInput.addEventListener("change", () => {
        if (stateInput.value !== "") clearError(stateInput);
    });
    countryInput.addEventListener("change", () => {
        if (countryInput.value !== "") clearError(countryInput);
    });

    documentInput.addEventListener("change", () => {
        if (documentInput.files[0]) clearError(documentInput);
    });

    departmentInput.addEventListener("change", () => {
        if (departmentInput.value !== "") clearError(departmentInput);
    });

    userNameInput.addEventListener("input", () => {
        if (userNameInput.value.trim() !== "") clearError(userNameInput);
    });
    passInput.addEventListener("input", () => {
        if (passInput.value.trim() !== "") clearError(passInput);
    });
    contactInput.addEventListener("input", () => {
        if (contactInput.value.trim() !== "") clearError(contactInput);
    });
    highInput.addEventListener("input", () => {
        if (highInput.value.trim() !== "") clearError(highInput);
    });
    interInput.addEventListener("input", () => {
        if (interInput.value.trim() !== "") clearError(interInput);
    });
    branchInput.addEventListener("change", () => {
        if (branchInput.value !== "") clearError(branchInput);
    });
    termsCheckbox.addEventListener("change", () => {
        if (termsCheckbox.checked) clearError(termsCheckbox);
    });
};

document.addEventListener("DOMContentLoaded", () => {
    navLeftGroup.style.display = "none";
    updateStepVisibility();
    realtimeValidation();

    navRightGroup.addEventListener("click", () => {
        if (currentFormStep < formStepsID.length - 1) {
            if (validateStep(currentFormStep)) {
                currentFormStep++;
                updateStepVisibility();
            }
        }
    });

    navLeftGroup.addEventListener("click", () => {
        if (currentFormStep > 0) {
            currentFormStep--;
            updateStepVisibility();
        }
    });

    Object.keys(editsButtons).forEach((buttonId) => {
        const button = document.getElementById(buttonId);
        button.addEventListener("click", (e) => {
            e.preventDefault();
            currentFormStep = editsButtons[buttonId];
            updateStepVisibility();
        });
    });
});



////cad======

       /* Created by Tivotal */

       let carousel = document.querySelector(".carousel-c");
       let btns = document.querySelectorAll(".wrapper-c i");
       let carouselChildren = [...carousel.children];
       let wrapper = document.querySelector(".wrapper-c");

       //getting card width
       let cardWidth = carousel.querySelector(".card").offsetWidth;
       let isDragging = false,
           startX,
           startScrollLeft,
           isAutoPlay = true,
           timeoutId;

       //getting number of cards can fit in carousel at once
       let cardsPerView = Math.round(carousel.offsetWidth / cardWidth);

       //inserting copied few last cards to beggining of carousel for infinite scrolling
       carouselChildren
           .slice(-cardsPerView)
           .reverse()
           .forEach((card) => {
               carousel.insertAdjacentHTML("afterbegin", card.outerHTML);
           });

       //inserting copied few first cards to end of the carousel for infinite scrolling
       carouselChildren.slice(0, cardsPerView).forEach((card) => {
           carousel.insertAdjacentHTML("beforeend", card.outerHTML);
       });

       btns.forEach((btn) => {
           btn.addEventListener("click", () => {
               //if the clicked button id is left scrolling carousel towards left by card width else towards right by card width
               carousel.scrollLeft += btn.id == "left" ? -cardWidth : cardWidth;
           });
       });

       let dragStart = (e) => {
           isDragging = true;

           carousel.classList.add("dragging");

           //recording initial cursor and scroll position
           startX = e.pageX;
           startScrollLeft = carousel.scrollLeft;
       };

       let dragging = (e) => {
           //returning here if the isDragging value is false
           if (!isDragging) return;

           //scrolling carousel according to mouse cursor
           carousel.scrollLeft = startScrollLeft - (e.pageX - startX);
       };

       let dragStop = () => {
           isDragging = false;

           carousel.classList.remove("dragging");
       };

       let infiniteScroll = () => {
           //if the carousel is at begining, scroll to end
           //else carousel at end , scroll to beginning
           if (carousel.scrollLeft === 0) {
               carousel.classList.add("no-transition");
               carousel.scrollLeft = carousel.scrollWidth - 2 * carousel.offsetWidth;
               carousel.classList.remove("no-transition");
           } else if (
               Math.ceil(carousel.scrollLeft) ===
               carousel.scrollWidth - carousel.offsetWidth
           ) {
               carousel.classList.add("no-transition");
               carousel.scrollLeft = carousel.offsetWidth;
               carousel.classList.remove("no-transition");
           }

           //clearing timeout & starting auto play if the mouse is not hovering the carousel
           clearTimeout(timeoutId);
           if (!wrapper.matches(":hover")) autoPlay();
       };

       let autoPlay = () => {
           //if the device is not mobile or tab, enabling auto play
           if (window.innerWidth < 800 || !isAutoPlay) return; //returning if the device is not desktop & isAutoPlay is false

           //autoplaying the carousel after every 2500 ms
           timeoutId = setTimeout(() => {
               carousel.scrollLeft += cardWidth;
           }, 2500);
       };

       autoPlay();

       carousel.addEventListener("mousedown", dragStart);
       carousel.addEventListener("mousemove", dragging);
       document.addEventListener("mouseup", dragStop);
       carousel.addEventListener("scroll", infiniteScroll);

       //auto play will be active only when there is no hover on carousel
       wrapper.addEventListener("mouseenter", () => clearTimeout(timeoutId));
       wrapper.addEventListener("mouseleave", autoPlay);

       // Counter for Numbers 1 to Number of Images And Number Of Images
       let counter = 1, numOfImgs = document.querySelectorAll(".images-container img").length;

       let flag = 1;

       function change() {

           // Get Radio and Checked
           document.getElementById("radio-" + counter).checked = true;

           // Increment or Decrement Counter to Get Next Radio
           flag ? counter++ : counter--;

           // If Reach to End then Start In Reverse
           if (counter > numOfImgs) {
               counter = numOfImgs - 1;
               flag = 0;
           }

           // If Reach to Start then Start Again
           if (counter < 1) {
               counter = 2;
               flag = 1;
           }

           // Call Again to Change Function
           setTimeout(change, 3000);
       }
       const sliderControls = document.querySelector(".slider-controls");
       const sliderTabs = sliderControls.querySelectorAll(".slider-tab");
       const sliderIndicator = sliderControls.querySelector(".slider-indicator");

       // Update the indicator
       const updateIndicator = (tab, index) => {
           document.querySelector(".slider-tab.current")?.classList.remove("current");
           tab.classList.add("current");

           sliderIndicator.style.transform = `translateX(${tab.offsetLeft - 20}px)`;
           sliderIndicator.style.width = `${tab.getBoundingClientRect().width}px`;

           // Calculate the scroll position and scroll smoothly
           const scrollLeft = sliderTabs[index].offsetLeft - sliderControls.offsetWidth / 2 + sliderTabs[index].offsetWidth / 2;
           sliderControls.scrollTo({ left: scrollLeft, behavior: "smooth" });
       }

       // Initialize swiper instance
       const swiper = new Swiper(".slider-container", {
           effect: "fade",
           speed: 1300,
           autoplay: { delay: 4000 },
           navigation: {
               prevEl: "#slide-prev",
               nextEl: "#slide-next",
           },
           on: {
               // Update indicator on slide change
               slideChange: () => {
                   const currentTabIndex = [...sliderTabs].indexOf(sliderTabs[swiper.activeIndex]);
                   updateIndicator(sliderTabs[swiper.activeIndex], currentTabIndex);
               },
               reachEnd: () => swiper.autoplay.stop(),
           },
       });

       // Update the slide on tab click
       sliderTabs.forEach((tab, index) => {
           tab.addEventListener("click", () => {
               swiper.slideTo(index);
               updateIndicator(tab, index);
           });
       });

       updateIndicator(sliderTabs[0], 0);
       window.addEventListener("resize", () => updateIndicator(sliderTabs[swiper.activeIndex], 0));
   


