function InterNodalAdmin() {

    var args = Array.prototype.slice.call(arguments),
            callback = args.pop(),
            modules = (args[0] && typeof args[0] === "string") ? args : args[0], i;

    if (!(this instanceof InterNodalAdmin)) {
        return new InterNodalAdmin(modules, callback);
    }
    this.web_address = '--ADDRESS--';
    this.html_get_address = '--ADDRESS--/html';
    this.html_post_address = '--ADDRESS--/html-p';
    this.json_address = '--ADDRESS--/json';
    this.json_post_address = '--ADDRESS--/data';
    this.html_content_headers = new Headers({'Content-Type': 'text/html; charset=utf-8'});
    this.json_content_headers = new Headers({'Content-Type': 'application/json'});
    this.get_options = function (headers) {
        return {
            method: 'GET',
            mode: 'cors',
            cache: 'no-cache',
            credentials: 'include',
            headers: headers
        };
    };
    this.post_options = function (headers, content) {
        return {
            method: 'POST',
            mode: 'cors',
            cache: 'no-cache',
            credentials: 'include',
            headers: headers,
            redirect: 'follow', // manual, *follow, error
            referrer: 'no-referrer', // no-referrer, *client
            body: content
        };
    };

    if (!modules || modules === '*') {
        modules = [];
        for (i in InterNodalAdmin.modules) {
            if (InterNodalAdmin.modules.hasOwnProperty(i)) {
                modules.push(i);
            }
        }
    }

    for (i = 0; i < modules.length; i += 1) {
        InterNodalAdmin.modules[modules[i]](this);
    }

    callback(this);
}

InterNodalAdmin.modules = {};

InterNodalAdmin.modules.url = function (app) {
    app.get_as_json = function (url) {
        return url.replace('html', 'json');
    };
    app.api_url = function (params) {
        let url = app.web_address + '/api';
        url = params['service_type'] !== undefined ? url + '/' + params['service_type'] : url;
        url = params['content_type'] !== undefined ? url + '/' + params['content_type'] : url;
        url = params['context'] !== undefined ? url + '/' + params['context'] : url;
        url = params['object_type'] !== undefined ? url + '/' + params['object_type'] : url;
        url = params['value_type'] !== undefined ? url + '/' + params['value_type'] : url;
        url = params['value'] !== undefined ? url + '/' + params['value'] : url;
        return url;
    };
    app.get_html_meta_context_list = function () {
        return app.web_address + '/api/meta/html';
    };
    app.get_html_meta_object_list = function (context) {
        return app.web_address + '/api/meta/html/' + context;
    };
    app.get_html_meta_member_list = function (context, object_type) {
        return app.web_address + '/api/meta/html/' + context + '/' + object_type;
    };
    app.get_html_meta_member_type = function (context, member_type) {
        return app.web_address + '/api/meta/html/' + context + '/' + object_type + '/' + member_type;
    };
    app.get_html_meta_member_details = function (context, member_type) {
        return app.web_address + '/api/meta/html/' + context + '/' + object_type + '/' + details;
    };
    app.get_html_data_list_value_type = function (context, object_type, value_type) {
        return app.web_address + '/api/data/html/' + context + '/' + object_type + '/' + value_type;
    };
    app.get_html_url_object_type_form = function (context, object_type) {
        return app.web_address + '/api/meta/html/' + context + '/' + object_type + '/details';
    };
    app.get_html_url_repo_view = function (context, view) {
        return app.web_address + '/api/repo/html/' + context;
    };
    app.post_file_upload_form = function (context, file_type, entity_type) {
        return app.web_address + '/api/data/form/upload/' + context + '/' + file_type + '/' + entity_type;
    };

    /*
     app.get_html_url_values_list = function(context,object_type,value_type) {
     return app.html_get_address + '/list/'+ context +'/'+object_type+'/'+value_type;
     };
     */
    /*
     app.get_html_url_object_type_form = function(context,object_type) {
     return app.html_get_address +'/meta/'+ context +'/'+object_type;
     };
     */
    app.get_html = function (context, object_type) {
        return app.html_get_address + '/' + context + '/' + object_type;
    };
    app.get_html_url_contexts = function () {
        return app.html_get_address + '/meta/*';
    };
    app.get_html_url_object_types = function (context) {
        return app.html_get_address + '/meta/' + context + '/*';
    };
    app.get_html_url_member_types = function (context, object_type) {
        return app.html_get_address + '/meta/' + context + '/' + object_type + '/*';
    };
    /*
     app.get_html_url_repo_view = function(context,view) {
     return app.html_get_address + '/repo/stats/'+ context +'/'+view;
     };
     */
    app.post_json_url_request = function (context) {
        return app.json_post_address + '/request/' + context;
    };
    app.post_html_url_request = function (context) {
        return app.html_post_address + '/request/' + context;
    };
    app.post_html_events_url_request = function (context) {
        return app.html_post_address + '/events/' + context;
    };
    app.post_json_url_update = function (context, object_type) {
        return app.json_post_address + '/update/' + context + '/' + object_type;
    };
    /*
     app.post_file_upload_form = function(context,file_type) {
     return app.html_post_address +'/upload/'+context+'/'+file_type;
     };
     */
};


InterNodalAdmin.modules.talk = function (app) {
    app.json_request = function (url, json, callback) {
        let request = new Request(url, app.post_options(app.json_content_headers, json));
        fetch(request)
                .then(function (response) {
                    return response.text();
                }).then(function (data) {
            callback(data);
        });
    };
    app.post_json_respond_json = function (url, request_as_json, callback) {
        let request = new Request(url, app.post_options(app.json_content_headers, request_as_json));
        fetch(request)
                .then(function (response) {
                    return response.text();
                }).then(function (data) {
            callback(data);
        });
    };
    app.post_json_respond_html = function (url, request_as_json, callback) {
        let request = new Request(url, app.post_options(app.json_content_headers, request_as_json));
        fetch(request)
                .then(function (response) {
                    return response.text();
                }).then(function (data) {
            callback(data);
        });
    };
    app.html_request = function (url, callback) {
        let request = new Request(url, app.get_options(app.html_content_headers));
        fetch(request)
                .then(function (response) {
                    return response.text();
                }).then(function (data) {
            callback(data);
        });
    };
};



InterNodalAdmin.modules.dom = function (app) {
    app.find_foreign_key = function (context, form_element) {
        let foreignKeyElement = form_element.querySelector('.ForeignKey');
        let results = [];
        if (foreignKeyElement !== null && foreignKeyElement.classList.length > 1) {
            for (let i = 0; i < foreignKeyElement.classList.length; i = i + 1) {
                if (foreignKeyElement.classList[i].startsWith(context + '.')) {
                    let fk_object = foreignKeyElement.classList[i] + '.' + foreignKeyElement.name;
                    let value = {
                        target_form_id: form_element.id,
                        target_element_id: foreignKeyElement.id,
                        fk_owner: fk_object
                    };
                    results.push(value);
                }
            }
        }
        return results;
    };
    app.get_value = function (element_id) {
        let element = document.getElementById(element_id);
        if (element !== undefined) {
            if (element.tagName === 'DIV') {
                let selectElement = element.querySelector('select');
                if (selectElement !== undefined) {
                    let collection = selectElement.selectedOptions;
                    if (collection.length >= 1) {
                        return collection[0].value;
                    }
                }
                let inputElement = element.querySelector('input');
                if (inputElement !== undefinded) {
                    return inputElement.value;
                }
            }
            if (element.tagName === 'SELECT') {
                let collection = element.selectedOptions;
                if (collection.length >= 1) {
                    return collection[0].value;
                }
            }
            if (element.tagName === 'INPUT') {
                return element.value;
            }
        }
        return undefined;
    };
    app.selected_value = function (element_id) {
        let element = document.getElementById(element_id);
        if (element !== undefined) {
            let selectElement = element.querySelector('select');
            let collection = selectElement.selectedOptions;
            if (collection.length >= 1) {
                return collection[0].value;
            }
        }
        return undefined;
    };
    app.attach_html = function (element_id, rawHTML, callback, clearTarget) {
        var stagingElement = document.createElement('div');
        stagingElement.insertAdjacentHTML('afterbegin', rawHTML);
        var newElement = stagingElement.firstElementChild;
        var element = document.getElementById(element_id);
        if (element && element !== undefined) {
            if (clearTarget && element.textContent && element.textContent !== undefined) {
                element.textContent = '';
            }
            element.appendChild(newElement);
            if (callback) {
                callback(newElement);
            }
        } else {
            console.error('element_id = ' + element_id + ' was not found');
        }
    };
    app.append_html = function (element_id, rawHTML, callback) {
        app.attach_html(element_id, rawHTML, callback, false);
    };
    app.replace_html = function (element_id, rawHTML, callback) {
        app.attach_html(element_id, rawHTML, callback, true);
    };
    app.bind_element = function (element, listener, event) {
        element.addEventListener(event, listener, {passive: true});
    };
    app.bind_element_id = function (element_id, listener, event) {
        let element = document.getElementById(element_id);
        if (element !== null && element !== undefined) {
            let inputElement = element;
            if (inputElement.tagName !== 'INPUT' || inputElement.tagName !== 'SELECT') {
                inputElement = element.querySelector('input');
                if (!inputElement) {
                    inputElement = element.querySelector('select');
                }
            }
            if (inputElement) {
                inputElement.addEventListener(event, listener, {passive: true});
            }
        }
    };
    app.bind_input_event = function (element_id, listener, event) {
        event = event || 'change';
        let element = document.getElementById(element_id);
        if (element !== null && element !== undefined) {
            let inputElement = element;
            if (inputElement.tagName !== 'INPUT') {
                inputElement = element.querySelector('input');
            }
            if (inputElement) {
                inputElement.addEventListener(event, listener, {passive: true});
                return;
            }
        } else {
            if (element_id !== null) {
                console.error('Unable to find input with id="' + element_id + '"');
            }
        }
    };
    app.bind_select_event = function (element_id, listener) {
        let element = document.getElementById(element_id);
        if (element !== null && element !== undefined) {
            let selectElement = element;
            if (selectElement.tagName !== 'SELECT') {
                selectElement = element.querySelector('select');
            }
            if (selectElement) {
                selectElement.addEventListener('change', listener, {passive: true});
                return;
            }
        } else {
            if (element_id !== null) {
                console.error('Unable to find select with id="' + element_id + '"');
            }
        }
    };
    app.bind_button_event = function (element_id, listener) {
        let element = document.getElementById(element_id);
        if (element !== null && element !== undefined) {
            let inputElement = element;
            if (inputElement && (inputElement.tagName !== 'BUTTON' && (inputElement.tagName !== 'INPUT' || inputElement.type !== 'button'))) {
                inputElement = element.querySelector('button');
                if (inputElement && inputElement.tagName !== 'BUTTON') {
                    console.error('Unable to find button with id="' + element_id + '"');
                }
            }
            inputElement.addEventListener('click', listener);
        } else {
            if (element_id !== null) {
                console.error('Unable to find button with id="' + element_id + '"');
            }
        }
    };
}
