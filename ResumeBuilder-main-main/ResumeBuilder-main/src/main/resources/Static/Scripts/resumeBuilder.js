document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("resumeForm");

    // Map input fields to preview IDs
    const fieldMap = {
      name: "Pname",
      email: "Pemail",
      phone: "contact",
      address: "Paddress",
      link: "Plink",
      url:"Purl",
      companyName:"PcompanyName",
      jobTitle:"PjobTitle",
      location:"Clocation",
      startDate:"CstartDate",
      endDate:"CendDate",
      description:"Pdescription",
      institute:"Pinstitute",
      Elocation:"Plocation",
      degreeType:"PdegreeType",
      score:"Pscore",
      fieldOfStudy:"PfieldOfStudy",
      EstartDate:"PstartDate",
      EendDate:"PendDate",
      languages: "Planguages",
      lib: "Plib",
      tools: "Ptools",

    };

    // Attach event listeners to form fields
    Object.keys(fieldMap).forEach((fieldId) => {
      const inputField = document.getElementById(fieldId);
      const previewField = document.getElementById(fieldMap[fieldId]);

      if (inputField && previewField) {
        inputField.addEventListener("input", () => {
          previewField.textContent = inputField.value || `[${inputField.placeholder}]`;
        });
      }
    });

    // Dynamic updates for Work Experience and Education sections
    
    
    
    const addDynamicSection = (sectionId, previewId, inputFields) => {
      const section = document.getElementById(sectionId);
      const previewSection = document.getElementById(previewId);

      section.querySelector("button").addEventListener("click", (e) => {
        e.preventDefault(); // Prevent form submission
        const entry = document.createElement("li");
        const values = inputFields.map((fieldId) => document.getElementById(fieldId).value);
        entry.textContent = values.join(" | ");
        previewSection.appendChild(entry);
      });
    };

    addDynamicSection("experience-section", "work-experience", [
      "companyName",
      "JobTitle",
      "location",
      "startDate",
      "endDate",
      "description",
    ]);

    addDynamicSection("education-section", "education", [
      "institute",
      "location",
      "degreeType",
      "fieldOfStudy",
      "startDate",
      "endDate",
      "score",
    ]);
  });