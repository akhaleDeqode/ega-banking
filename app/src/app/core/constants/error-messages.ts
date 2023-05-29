export class ErrorMessages {

    required = (label: string) => {
        return `${label} is required`;
    }

    minLength = (minValue: number) => {
        return `Atleast ${minValue} characters are required`;
    }

    maxLength = (maxValue: number) => {
        return `Maximum ${maxValue} characteres are allowed`;
    }

    invalid = (label: string) => {
        return `${label} is invalid`;
    }

    passwordPattern = () => {
        return `Must contain One uppercase, lowercase, number and symbol`;
    }
}