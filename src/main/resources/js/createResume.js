document.addEventListener('DOMContentLoaded', function () {
    let workExpCounter = 0;
    let educationCounter = 0;
    let contactsCounter = 0;

    function createCard(container) {
        const cardBody = document.createElement('div');
        cardBody.className = 'card-body';

        let inputFields = [
            { type: 'text', name: 'position', label: 'Position' },
            { type: 'text', name: 'companyName', label: 'Company name' },
            { type: 'textarea', name: 'responsibilities', label: 'Responsibilities' },
            { type: 'number', name: 'years', label: 'Years' }
        ];

        if (container === 'workExp') {
            inputFields.forEach(field => {
                const inputElement = createInputField(field.type, `workExpInfo[${workExpCounter}].${field.name}`, field.label);
                cardBody.appendChild(inputElement);
            });
            workExpCounter++;
        } else if (container === 'education') {
            inputFields = [
                { type: 'text', name: 'degree', label: 'Degree' },
                { type: 'text', name: 'institution', label: 'Institution' },
                { type: 'textarea', name: 'program', label: 'Program' },
                { type: 'date', name: 'startDate', label: 'Starting date' },
                { type: 'date', name: 'endDate', label: 'Ending date' }
            ];
            inputFields.forEach(field => {
                const inputElement = createInputField(field.type, `educationInfo[${educationCounter}].${field.name}`, field.label);
                cardBody.appendChild(inputElement);
            });
            educationCounter++;
        } else if (container === 'addContacts') {
            inputFields = [
                { type: 'text', name: 'type', label: 'Contact type' },
                { type: 'text', name: 'infoValue', label: 'Contact value' }
            ];
            inputFields.forEach(field => {
                const inputElement = createInputField(field.type, `contacts[${contactsCounter}].${field.name}`, field.label);
                cardBody.appendChild(inputElement);
            });
            contactsCounter++;
        }

        let closeButton = document.createElement('button');
        closeButton.className = 'btn btn-danger card-close';
        closeButton.textContent = 'Delete';
        closeButton.addEventListener('click', () => {
            cardBody.remove();
        });

        cardBody.appendChild(closeButton);

        document.getElementById(container).appendChild(cardBody);
    }

    function createInputField(type, name, label) {
        const container = document.createElement('div');
        container.className = 'mb-3';

        const labelElement = document.createElement('label');
        labelElement.textContent = label;
        labelElement.className = 'form-label';
        container.appendChild(labelElement);

        const inputElement = document.createElement(type === 'textarea' ? 'textarea' : 'input');
        inputElement.type = type === 'textarea' ? 'text' : type;
        inputElement.name = name;
        inputElement.className = 'form-control';
        container.appendChild(inputElement);

        return container;
    }

    document.getElementById('add-work-btn').addEventListener('click', () => {
        createCard('workExp');
    });

    document.getElementById('add-education-btn').addEventListener('click', () => {
        createCard('education');
    });

    document.getElementById('add-contacts-btn').addEventListener('click', () => {
        createCard('addContacts');
    });

});
