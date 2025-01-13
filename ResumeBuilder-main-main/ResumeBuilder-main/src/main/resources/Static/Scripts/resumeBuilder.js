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
	
    
    
 });
 let linkindex  = 2;
 let workindex  = 2;
 let edindex  = 2;
 function addLinkField() {
        // Create a new div for the link section
        const linkDiv = document.createElement('div');
        // Get the current index for link
        
        // Set up the inner HTML for the new link section with indexed names for form submission
        linkDiv.innerHTML = ` <div class="section" id="linkSection">
            <label for="link${linkindex  }">Link ${linkindex  }</label>
            <input type="text" id="link${linkindex  }" name="links[${linkindex-1 }].url" placeholder="Enter link" required>

            <label for="url${linkindex  }">URL ${linkindex  }</label>
            <input type="text" id="url${linkindex  }" name="links[${linkindex -1}].url" placeholder="Enter URL" required>
			</div>
			
        `;
     
        // Append the new link div to the link section
        document.getElementById('linkSection').appendChild(linkDiv);
		
		addRealTimeListeners(linkindex)
		linkindex++;
    }
	
	function addRealTimeListeners(index) {
	      // Get the new inputs
	      const linkInput = document.getElementById(`link${index}`);
	      const urlInput = document.getElementById(`url${index}`);
	  
	      // Find the links list in the header
	      const linksList = document.querySelector("header ul");
	  
	      // Create a new list item for the link
	      const listItem = document.createElement("li");
	      listItem.id = `linkItem${index}`;
	      listItem.innerHTML = `<span id="linkName${index}">[Link ${index}]</span>: <span id="linkURL${index}">[URL ${index}]</span>`;
	      linksList.appendChild(listItem);
	  
	      // Add input event listeners to update the header in real time
	      linkInput.addEventListener("input", () => {
	          document.getElementById(`linkName${index}`).textContent = linkInput.value || `[Link ${index}]`;
	      });
	  
	      urlInput.addEventListener("input", () => {
	          document.getElementById(`linkURL${index}`).textContent = urlInput.value || `[URL ${index}]`;
	      });
	  }

    // Function to add a new work experience field dynamically
    function addWorkExperienceField() {
        const workDiv = document.createElement('div');
     
        
        // Set up the inner HTML for the new work experience section with indexed names for form submission
        workDiv.innerHTML = `
            <label for="companyName${workindex}">Company Name ${workindex }</label>
            <input type="text" id="companyName${workindex }" name="workExperience[${workindex-1}].companyName" placeholder="Enter company name" required>

            <label for="jobTitle${workindex }">Job Title ${workindex }</label>
            <input type="text" id="jobTitle${workindex }" name="workExperience[${workindex-1}].jobTitle" placeholder="Enter job title" required>

            <label for="location${workindex }">Location ${workindex }</label>
            <input type="text" id="location${workindex }" name="workExperience[${workindex-1}].location" placeholder="Enter location" required>

            <label for="startDate${workindex }">Start Date ${workindex }</label>
            <input type="date" id="startDate${workindex }" name="workExperience[${workindex-1}].startDate" placeholder="Enter start date">

            <label for="endDate${workindex }">End Date ${workindex }</label>
            <input type="date" id="endDate${workindex }" name="workExperience[${workindex-1}].endDate" placeholder="Enter end date">

            <label for="description${workindex }">Description ${workindex }</label>
            <input type="text" id="description${workindex }" name="workExperience[${workindex-1}].description" placeholder="Enter job description" required>
        `;
        
        // Append the new work experience div to the work experience section
        document.getElementById('workExperienceSection').appendChild(workDiv);
		addWorkSection(workindex);
		workindex++;
		
    }
	function addWorkSection(index) {
	    const workPreviewList = document.querySelector("#work-experience ul");

	    const listItem = document.createElement("section");
	    listItem.id = `workPreview${index}`;
	    listItem.innerHTML = `
	      
	         <section id="work-experience" class="section">
	            <h2>Work Experience ${index}</h2>
	            <ul >
	                
	                <li>
	                    <strong>Company Name:</strong> <span id="PcompanyName${index}" >[Company Name]</span>
	                </li>
	                <li>
	                    <strong>Job Title:</strong> <span id="PjobTitle${index}" >[Job Title]</span>
	                </li>
	                <li>
	                    <strong>Location:</strong> <span id="Clocation${index}" >[Location]</span>
	                </li>
	                <li>
	                    <strong>Start Date:</strong> <span id="CstartDate${index}" >[Start Date]</span>
	                </li>
	                <li>
	                    <strong>End Date:</strong> <span  id="CendDate${index}">[End Date]</span>
	                </li>
	                <li>
	                    <strong>Description:</strong> <span id="Pdescription${index}" >[Job Description]</span>
	                </li>
	            </ul>
	        </section>
	    `;
	    
	    workPreviewList.appendChild(listItem);
	    // Add listeners for real-time updates
	    const companyNameField = document.getElementById(`companyName${index}`);
	    const jobTitleField = document.getElementById(`jobTitle${index}`);
	    const locationField = document.getElementById(`location${index}`);
	    const startDateField = document.getElementById(`startDate${index}`);
	    const endDateField = document.getElementById(`endDate${index}`);
	    const descriptionField = document.getElementById(`description${index}`);

	    companyNameField.addEventListener("input", () => {
	        document.getElementById(`PcompanyName${index}`).textContent = companyNameField.value || "[Company Name]";
	    });

	    jobTitleField.addEventListener("input", () => {
	        document.getElementById(`PjobTitle${index}`).textContent = jobTitleField.value || "[Job Title]";
	    });

	    locationField.addEventListener("input", () => {
	        document.getElementById(`Clocation${index}`).textContent = locationField.value || "[Location]";
	    });

	    startDateField.addEventListener("input", () => {
	        document.getElementById(`CstartDate${index}`).textContent = startDateField.value || "[Start Date]";
	    });

	    endDateField.addEventListener("input", () => {
	        document.getElementById(`CendDate${index}`).textContent = endDateField.value || "[End Date]";
	    });

	    descriptionField.addEventListener("input", () => {
	        document.getElementById(`Pdescription${index}`).textContent = descriptionField.value || "[Job Description]";
	    });
	}

    // Function to add a new education field dynamically
    function addEducationField() {
        const educationDiv = document.createElement('div');
       
        
        // Set up the inner HTML for the new education section with indexed names for form submission
        educationDiv.innerHTML = `
            <label for="institute${edindex}">Institute Name ${edindex }</label>
            <input type="text" id="institute${edindex}" name="education[${edindex-1}].instituteName" placeholder="Enter institute name" required>

            <label for="Elocation${edindex}">Location ${edindex}</label>
            <input type="text" id="Elocation${edindex}" name="education[${edindex-1}].location" placeholder="Enter location" required>

            <label for="degreeType${edindex}">Degree Type ${edindex}</label>
            <input type="text" id="degreeType${edindex}" name="education[${edindex-1}].degreeType" placeholder="Enter degree type" required>

            <label for="fieldOfStudy${edindex}">Field of Study ${edindex}</label>
            <input type="text" id="fieldOfStudy${edindex}" name="education[${edindex-1}].fieldOfStudy" placeholder="Enter field of study" required>

            <label for="EstartDate${edindex}">Start Date ${edindex}</label>
            <input type="date" id="EstartDate${edindex}" name="education[${edindex-1}].startDate" placeholder="Enter start date">

            <label for="EendDate${edindex}">End Date ${edindex}</label>
            <input type="date" id="EendDate${edindex}" name="education[${edindex-1}].endDate" placeholder="Enter end date">

            <label for="score${edindex}">Score ${edindex}</label>
            <input type="text" id="score${edindex}" name="education[${edindex-1}].score" placeholder="Enter score" required>
        `;
        
        // Append the new education div to the education section
        document.getElementById('educationSection').appendChild(educationDiv);
		
		
		addEducationRealTimeListeners(edindex);
		edindex++;
    }

	function addEducationRealTimeListeners(index) {

	    const educationList = document.querySelector("#education ul");

	    const listItem = document.createElement("section");
	    listItem.id = `educationPreview${index}`;
	    listItem.innerHTML = `
	         <section id="education" class="section">
	            <h2>Education${index}</h2>
	            <ul  >
	                
	                <li>
	                    <strong>Institute Name:</strong> <span id="Pinstitute${index}" >[Institute Name]</span>
	                </li>
	                <li>
	                    <strong>Location:</strong> <span id="Plocation${index}" >[Location]</span>
	                </li>
	                <li>
	                    <strong>Degree Type:</strong> <span  id="PdegreeType${index}">[Degree Type]</span>
	                </li>
	                <li>
	                    <strong>Field of Study:</strong> <span  id="PfieldOfStudy${index}">[Field of Study]</span>
	                </li>
	                <li>
	                    <strong>Start Date:</strong> <span  id="PstartDate${index}">[Start Date]</span>
	                </li>
	                <li>
	                    <strong>End Date:</strong> <span id="PendDate${index}" >[End Date]</span>
	                </li>
	                <li>
	                    <strong>Score:</strong> <span  id="Pscore${index}">[Score]</span>
	                </li>
	            </ul>
	        </section>
	    `;
	    educationList.appendChild(listItem);



	    const instituteField = document.getElementById(`institute${index}`);
	    const locationField = document.getElementById(`Elocation${index}`);
	    const degreeTypeField = document.getElementById(`degreeType${index}`);
	    const fieldOfStudyField = document.getElementById(`fieldOfStudy${index}`);
	    const startDateField = document.getElementById(`EstartDate${index}`);
	    const endDateField = document.getElementById(`EendDate${index}`);
	    const scoreField = document.getElementById(`score${index}`);

	    // Add listeners for real-time updates
	    instituteField.addEventListener("input", () => {
	        document.getElementById(`Pinstitute${index}`).textContent = instituteField.value || "[Institute Name]";
	    });

	    locationField.addEventListener("input", () => {
	        document.getElementById(`Plocation${index}`).textContent = locationField.value || "[Location]";
	    });

	    degreeTypeField.addEventListener("input", () => {
	        document.getElementById(`PdegreeType${index}`).textContent = degreeTypeField.value || "[Degree Type]";
	    });

	    fieldOfStudyField.addEventListener("input", () => {
	        document.getElementById(`PfieldOfStudy${index}`).textContent = fieldOfStudyField.value || "[Field of Study]";
	    });

	    startDateField.addEventListener("input", () => {
	        document.getElementById(`PstartDate${index}`).textContent = startDateField.value || "[Start Date]";
	    });

	    endDateField.addEventListener("input", () => {
	        document.getElementById(`PendDate${index}`).textContent = endDateField.value || "[End Date]";
	    });

	    scoreField.addEventListener("input", () => {
	        document.getElementById(`Pscore${index}`).textContent = scoreField.value || "[Score]";
	    });
	}
